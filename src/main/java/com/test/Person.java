package com.test;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Author   yang_tao@<yangtao.letzgo.com.cn>
 * Date     2017-11-15 10:05
 * Version  1.0
 */
@Data
@Builder
public class Person implements Serializable {

    private String name;

    private Integer age;

    private Integer sex;

    private Date birthDay;

}
