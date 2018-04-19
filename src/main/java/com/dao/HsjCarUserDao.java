package com.dao;

import com.mapper.HsjCarUserMapper;
import com.visionet.domain.CarUserAssocicat;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

/**
 * @author yang_tao@<yangtao.letzgo.com.cn>
 * @version 1.0
 * @date 2018-04-16 17:10
 */
@RegisterMapper(HsjCarUserMapper.class)
public interface HsjCarUserDao {

    @SqlQuery("SELECT ID,CID,ASSOCIATE_PHONE FROM t_car_user_association WHERE CID=:cid AND DEL_FLAG=1;")
    CarUserAssocicat select(@Bind("cid") Integer cid);

}
