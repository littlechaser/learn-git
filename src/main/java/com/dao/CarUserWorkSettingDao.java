package com.dao;

import com.mapper.CarUserWorkSettingMapper;
import com.visionet.domain.CarUserWorkSeting;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

/**
 * @author yang_tao@<yangtao.letzgo.com.cn>
 * @version 1.0
 * @date 2018-04-16 16:17
 */
@RegisterMapper(CarUserWorkSettingMapper.class)
public interface CarUserWorkSettingDao {

    @SqlQuery("SELECT USER_ID,ON_BOOK_TYPE,OFF_BOOK_TYPE,DEGRADED_TYPE,BUSINESS_TYPE,ROUTE_SWITCH FROM t_car_user_work_seting WHERE USER_ID=:userId;")
    CarUserWorkSeting select(@Bind("userId") Integer userId);

}
