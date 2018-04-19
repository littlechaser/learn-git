package com.dao;

import com.mapper.CompanyMapper;
import com.mapper.PushDesMapper;
import com.visionet.domain.Company;
import com.visionet.domain.PushDes;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;


/**
 * @author yang_tao@<yangtao.letzgo.com.cn>
 * @version 1.0
 * @date 2018-04-16 13:50
 */
@RegisterMapper(CompanyMapper.class)
public interface CompanyDao {

    @SqlQuery("SELECT COMPANY_ID FROM t_company WHERE PROPERTY_TYPE=0;")
    List<Company> select();

}
