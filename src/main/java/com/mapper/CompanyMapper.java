package com.mapper;

import com.visionet.domain.Company;
import com.visionet.domain.PushDes;
import org.skife.jdbi.v2.BaseResultSetMapper;

import java.util.Map;

/**
 * @author yang_tao@<yangtao.letzgo.com.cn>
 * @version 1.0
 * @date 2018-04-16 13:52
 */
public class CompanyMapper extends BaseResultSetMapper<Company> {

    @Override
    protected Company mapInternal(int i, Map<String, Object> map) {
        Company company = new Company();
        company.setCompanyId((Integer) map.get("COMPANY_ID"));
        company.setCompanyName((String) map.get("COMPANY_NAME"));
        company.setPropertyType((Integer) map.get("PROPERTY_TYPE"));
        return company;
    }

}
