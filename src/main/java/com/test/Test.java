package com.test;

import com.google.common.collect.Maps;
import com.visionet.core.redis.RedisUtil;
import com.visionet.core.util.HttpClient;

import java.util.*;

/**
 * Author   yang_tao@<yangtao.letzgo.com.cn>
 * Date     2017-12-19 0:07
 * Version  1.0
 */
public class Test {
    public static void main(String[] args) {
//        String authStr = "7f85c00bc2b629a06ebb12b7:f0408475dedcc26d04669c52";
//        Map<String, String> heads = Maps.newHashMap();
//        heads.put("Content-Type", "application/json");
//        heads.put("Authorization", "Basic " + org.apache.commons.codec.binary.Base64.encodeBase64String(authStr.getBytes()));
//        String post1 = HttpClient.https("https://device.jpush.cn" + "/v3/devices/" + "100d855909458968e70", "GET", null, heads);
//        System.out.println(post1);
//
//        String post2 = HttpClient.https("https://device.jpush.cn/v3/tags/0_0_107/" + "registration_ids/100d855909458968e70", "GET", null, heads);
//        System.out.println(post2);
        Stack<Integer> stack = new Stack<>();
        stack.push(6);
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());

    }
}
