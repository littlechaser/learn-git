package com.dao;

import com.mapper.PushDesMapper;
import com.visionet.domain.PushDes;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;


/**
 * @author yang_tao@<yangtao.letzgo.com.cn>
 * @version 1.0
 * @date 2018-04-16 13:50
 */
@RegisterMapper(PushDesMapper.class)
public interface PushDesDao {

    @SqlQuery("SELECT PHONE,USER_TYPE,CHANNEL_ID FROM t_push_des WHERE PHONE=:phone AND USER_TYPE=:userType LIMIT 1;")
    PushDes select(@Bind("phone") String phone, @Bind("userType") Integer userType);

}
