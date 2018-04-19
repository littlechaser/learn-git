package com.test;

import com.visionet.core.exception.BizDataException;
import com.visionet.core.redis.RedisUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Author   yang_tao@<yangtao.letzgo.com.cn>
 * Date     2017-11-21 15:28
 * Version  1.0
 */
public class TestVersion {
    public static void main(String[] args) {
        Set<String> keys = RedisUtil.getKeys("VERSION_3*");
        System.out.println(keys);
    }
}
