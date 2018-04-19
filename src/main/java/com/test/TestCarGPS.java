package com.test;

import com.visionet.core.lbs.CarLbsDto;
import com.visionet.core.redis.RedisUtil;

/**
 * Author   yang_tao@<yangtao.letzgo.com.cn>
 * Date     2017-11-22 11:25
 * Version  1.0
 */
public class TestCarGPS {
    public static void main(String[] args) {
        Object data = RedisUtil.getObjectData("107_carCoor-18600000001-object");
        if (data != null) {
            System.out.println(data.toString());
        }
    }
}
