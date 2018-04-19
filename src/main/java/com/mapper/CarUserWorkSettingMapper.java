package com.mapper;

import com.visionet.domain.CarUserWorkSeting;
import org.skife.jdbi.v2.BaseResultSetMapper;

import java.util.Map;

/**
 * @author yang_tao@<yangtao.letzgo.com.cn>
 * @version 1.0
 * @date 2018-04-16 16:17
 */
public class CarUserWorkSettingMapper extends BaseResultSetMapper<CarUserWorkSeting> {
    @Override
    protected CarUserWorkSeting mapInternal(int i, Map<String, Object> map) {
        CarUserWorkSeting carUserWorkSeting = new CarUserWorkSeting();
        carUserWorkSeting.setUserId((Integer) map.get("USER_ID"));
        carUserWorkSeting.setOnBookType((Integer) map.get("ON_BOOK_TYPE"));
        carUserWorkSeting.setOffBookType((Integer) map.get("OFF_BOOK_TYPE"));
        carUserWorkSeting.setBusinessType((Integer) map.get("BUSINESS_TYPE"));
        carUserWorkSeting.setDegradedType((Integer) map.get("DEGRADED_TYPE"));
        carUserWorkSeting.setRouteSwitch((Integer) map.get("ROUTE_SWITCH"));
        return carUserWorkSeting;
    }
}
