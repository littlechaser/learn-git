package com.mapper;

import com.visionet.domain.CarUserAssocicat;
import org.skife.jdbi.v2.BaseResultSetMapper;

import java.util.Map;

/**
 * @author yang_tao@<yangtao.letzgo.com.cn>
 * @version 1.0
 * @date 2018-04-16 13:52
 */
public class HsjCarUserMapper extends BaseResultSetMapper<CarUserAssocicat> {

    @Override
    protected CarUserAssocicat mapInternal(int i, Map<String, Object> map) {
        CarUserAssocicat carUser = new CarUserAssocicat();
        carUser.setId((Integer) map.get("ID"));
        carUser.setCid((Integer) map.get("CID"));
        carUser.setAssociatePhone((String) map.get("ASSOCIATE_PHONE"));
        return carUser;
    }

}
