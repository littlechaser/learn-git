package com.mapper;

import com.visionet.domain.CarUser;
import com.visionet.domain.Company;
import org.skife.jdbi.v2.BaseResultSetMapper;

import java.util.Map;

/**
 * @author yang_tao@<yangtao.letzgo.com.cn>
 * @version 1.0
 * @date 2018-04-16 13:52
 */
public class CarUserMapper extends BaseResultSetMapper<CarUser> {

    @Override
    protected CarUser mapInternal(int i, Map<String, Object> map) {
        CarUser carUser = new CarUser();
        carUser.setId((Integer) map.get("ID"));
        carUser.setPhone((String) map.get("PHONE"));
        carUser.setBusinessType((Integer) map.get("BUSINESS_TYPE"));
        carUser.setCompanyId((Integer) map.get("COMPANY_ID"));
        carUser.setCityId((Integer) map.get("CITY_ID"));
        carUser.setHsjAppFlag((String) map.get("HSJ_APP_FLAG"));
        return carUser;
    }

}
