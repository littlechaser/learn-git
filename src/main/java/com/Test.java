package com;


import com.allen.common.RedisUtil;

/**
 * Author   yang_tao@<yangtao.letzgo.com.cn>
 * Date     2018-01-22 17:20
 * Version  1.0
 */
public class Test {
    public static void main(String[] args) {
        RedisUtil.setData("allen", "1", 2);
        System.out.println(RedisUtil.getData("allen"));
    }

}