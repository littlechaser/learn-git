package com;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.dao.*;
import com.google.common.collect.Maps;
import com.visionet.core.util.HttpClient;
import com.visionet.domain.*;
import com.visionet.user.dto.AddAndRemoveTag;
import com.visionet.user.dto.AddAndRemoveTags;
import org.apache.commons.lang.StringUtils;
import org.skife.jdbi.v2.DBI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yang_tao@<yangtao.letzgo.com.cn>
 * @version 1.0
 * @date 2018-04-11 13:16
 */
public class UpdateJpushTag implements Runnable {
    String authStr = "d118c60ebd830894fe42a6b9:ca4c76245fc06dde2961bf5d";//出租
    String authStr2 = "ab623d76a6843744263265e4:ea3f03c9316dbbd82f040c1f";//后视镜
    Map<String, String> heads = Maps.newHashMap();
    Map<String, String> heads2 = Maps.newHashMap();
    DBI dbi;

    {
        heads.put("Content-Type", "application/json");
        heads.put("Authorization",
                "Basic " + org.apache.commons.codec.binary.Base64.encodeBase64String(authStr.getBytes()));
        heads2.put("Content-Type", "application/json");
        heads2.put("Authorization",
                "Basic " + org.apache.commons.codec.binary.Base64.encodeBase64String(authStr2.getBytes()));

    }


    private final List<CarUser> cars;
    private final List<Integer> selfRunList;
    private final int threadNum;
    private final ExecutorService executors;

    UpdateJpushTag(DBI dbi, List<CarUser> cars, List<Integer> selfRunList, int threadNum) {
        this.dbi = dbi;
        this.cars = cars;
        this.selfRunList = selfRunList;
        this.threadNum = threadNum;
        this.executors = Executors.newFixedThreadPool(threadNum);
    }

    @Override
    public void run() {
        while (true) {
            final CarUser carUser;
            boolean isLast = false;
            synchronized (cars) {
                int index = cars.size() - 1;
                if (index == 0) {
                    isLast = true;
                }
                if (!cars.isEmpty()) {
                    carUser = cars.get(index);
                    cars.remove(index);
                } else {
                    return;
                }
            }
            CarUserWorkSettingDao carUserWorkSettingDao = dbi.onDemand(CarUserWorkSettingDao.class);
            CarUserWorkSeting workSeting = carUserWorkSettingDao.select(carUser.getId());
            if (workSeting != null && (Objects.equals(workSeting.getOnBookType(), 1) || Objects.equals(workSeting.getOffBookType(), 1))) {
                final Integer cityId = carUser.getCityId();
                final Integer companyId = carUser.getCompanyId();
                AddAndRemoveTags tags = new AddAndRemoveTags();
                AddAndRemoveTag tag = new AddAndRemoveTag();
                List<String> add = new ArrayList<>();
                List<String> remove = new ArrayList<>();
                if (selfRunList.contains(companyId)) {
                    remove.add("0_0_" + cityId);
                    remove.add("0_0_" + cityId + "_VIP");
                    add.add("0_5_" + cityId);
                    add.add("0_5_" + cityId + "_VIP");
                } else {
                    remove.add("0_0_" + cityId);
                    add.add("0_5_" + cityId);
                }
                tag.setAdd(add);
                tag.setRemove(remove);
                tags.setTags(tag);
                boolean hsj = StringUtils.isNotBlank(carUser.getHsjAppFlag());
                String pushPhone = carUser.getPhone();
                if (hsj) {
                    HsjCarUserDao hsjCarUserDao = dbi.onDemand(HsjCarUserDao.class);
                    CarUserAssocicat associcat = hsjCarUserDao.select(carUser.getId());
                    pushPhone = associcat.getAssociatePhone();
                }
                PushDesDao pushDesDao = dbi.onDemand(PushDesDao.class);
                PushDes pushDes = pushDesDao.select(pushPhone, hsj ? 4 : 1);
                if (pushDes != null && StringUtils.isNotBlank(pushDes.getChannelId())) {
                    String channelId = pushDes.getChannelId();
                    //更新tag
                    if (StringUtils.isBlank(carUser.getHsjAppFlag())) {
                        HttpClient.https("https://device.jpush.cn/v3/devices/" + channelId, "POST", JSON.toJSONString(tags), heads);
                    } else {
                        HttpClient.https("https://device.jpush.cn/v3/devices/" + channelId, "POST", JSON.toJSONString(tags), heads2);
                    }
                    System.out.println("更新channelId=" + channelId + "的tag完成");
                }
            }
            if (isLast) {
                executors.shutdown();
                System.out.println("=================数据更新完成================");
            }
        }
    }

    public void execute() {
        for (int i = 0; i < threadNum; i++) {
            executors.execute(this);
        }
    }

    public static void main(String[] args) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://10.104.3.27/dzcxdb?useUnicode=true&characterEncoding=UTF-8");
        dataSource.setUsername("yangtao");
        dataSource.setPassword("yangtao");
        dataSource.setMaxActive(200);
        dataSource.setMinIdle(2);
        dataSource.setTestWhileIdle(true);
        DBI dbi = new DBI(dataSource);
        CarUserDao carUserDao = dbi.onDemand(CarUserDao.class);
        CompanyDao companyDao = dbi.onDemand(CompanyDao.class);
        List<Integer> selfList = new ArrayList<>();
        List<Company> companyList = companyDao.select();
        companyList.forEach(item -> selfList.add(item.getCompanyId()));
        UpdateJpushTag updateJpushTag = new UpdateJpushTag(dbi, carUserDao.select(), selfList, 100);
        updateJpushTag.execute();
    }
}
