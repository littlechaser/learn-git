package com.test;

import com.visionet.core.lbs.CarLbsDto;
import com.visionet.core.redis.RedisUtil;

import java.util.List;

/**
 * Author   yang_tao@<yangtao.letzgo.com.cn>
 * Date     2017-11-29 10:18
 * Version  1.0
 */
public class CarCoor {
    public static void main(String[] args) {
        List<Object> clds = RedisUtil.getObjectListData("atsfdhgfgjh");
        System.out.println(clds);
    }
}
