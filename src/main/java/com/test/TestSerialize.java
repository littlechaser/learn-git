package com.test;

import com.alibaba.fastjson.JSONObject;
import com.visionet.core.lbs.CarLbsDto;
import com.visionet.core.redis.RedisUtil;
import com.visionet.core.redis.SerializeUtil;

import java.io.UnsupportedEncodingException;

/**
 * Author   yang_tao@<yangtao.letzgo.com.cn>
 * Date     2017-12-18 13:25
 * Version  1.0
 */
public class TestSerialize {
    public static void main(String[] args) throws UnsupportedEncodingException {
        final Object data = RedisUtil.getObjectData("107_carCoor-13222222222-object");
        if (data != null) {
            final byte[] serialize = SerializeUtil.serialize(data);
            System.out.println("java序列化字节数：" + serialize.length);
            String str = JSONObject.toJSONString(data);
            System.out.println("json序列化字节数：" + str.getBytes("UTF-8").length);

            long start = System.currentTimeMillis();
            for (int i = 0; i < 100000; i++) {
                SerializeUtil.serialize(data);
            }
            long end = System.currentTimeMillis();
            System.out.println("java序列化耗时：" + (end - start));
            start = System.currentTimeMillis();
            for (int i = 0; i < 100000; i++) {
                JSONObject.toJSONString(data);
            }
            end = System.currentTimeMillis();
            System.out.println("json序列化耗时：" + (end - start));


            start = System.currentTimeMillis();
            for (int i = 0; i < 100000; i++) {
                SerializeUtil.unserialize(serialize);
            }
            end = System.currentTimeMillis();
            System.out.println("java反序列化耗时：" + (end - start));
            start = System.currentTimeMillis();
            for (int i = 0; i < 100000; i++) {
                JSONObject.parseObject(str);
            }
            end = System.currentTimeMillis();
            System.out.println("json反序列化耗时：" + (end - start));
        }
    }

}
