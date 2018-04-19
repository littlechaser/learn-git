package com.dao;

import com.mapper.CarUserMapper;
import com.visionet.domain.CarUser;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;


/**
 * @author yang_tao@<yangtao.letzgo.com.cn>
 * @version 1.0
 * @date 2018-04-16 13:50
 */
@RegisterMapper(CarUserMapper.class)
public interface CarUserDao {

    @SqlQuery("SELECT ID,PHONE,BUSINESS_TYPE,COMPANY_ID,CITY_ID,HSJ_APP_FLAG FROM t_car_user WHERE BUSINESS_TYPE=0 AND STATUS IN (1,7) AND IS_VALID=1 AND DEL_FLAG=1;")
    List<CarUser> select();

}
