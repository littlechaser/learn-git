package com.mapper;

import com.visionet.domain.PushDes;
import org.skife.jdbi.v2.BaseResultSetMapper;

import java.util.Map;

/**
 * @author yang_tao@<yangtao.letzgo.com.cn>
 * @version 1.0
 * @date 2018-04-16 13:52
 */
public class PushDesMapper extends BaseResultSetMapper<PushDes> {

    @Override
    protected PushDes mapInternal(int i, Map<String, Object> map) {
        PushDes pushDes = new PushDes();
        pushDes.setPhone((String) map.get("PHONE"));
        pushDes.setUserType((String) map.get("USER_TYPE"));
        pushDes.setChannelId((String) map.get("CHANNEL_ID"));
        return pushDes;
    }

}
