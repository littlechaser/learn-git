package com.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.visionet.core.util.HttpClient;

import java.util.Map;

/**
 * @author yang_tao@<yangtao.letzgo.com.cn>
 * @version 1.0
 * @date 2018-04-04 16:57
 */
public class TestJim {
    public static void main(String[] args) {
        String authStr = "d118c60ebd830894fe42a6b9:ca4c76245fc06dde2961bf5d";//出租
        Map<String, String> heads = Maps.newHashMap();
        heads.put("Content-Type", "application/json");
        heads.put("Authorization",
                "Basic " + org.apache.commons.codec.binary.Base64.encodeBase64String(authStr.getBytes()));

        //注册管理员
//        String admin = HttpClient.https("https://api.im.jpush.cn/v1/admins/", "POST", "{\"username\": \"admin\", \"password\": \"Admin123\"}", heads);
//        System.out.println(admin);
        //查询管理员
        String admin2 = HttpClient.https("https://api.im.jpush.cn/v1/admins?start=0&count=10", "GET", null, heads);
        System.out.println(admin2);
        //发消息
        JSONObject msg = new JSONObject();
        msg.put("version", 1);
        msg.put("target_type", "single");
        msg.put("target_id", "dora");
        msg.put("from_type", "admin");
        msg.put("from_id", "test_admin");
        msg.put("msg_type", "text");
        JSONObject body = new JSONObject();
        body.put("extras", new JSONObject());
        body.put("text", "Hello, JMessage!");
        msg.put("msg_body", body);
        String jsonStr = JSON.toJSONString(msg);
        System.out.println(jsonStr);
        String send = HttpClient.https("https://api.im.jpush.cn/v1/messages", "POST", jsonStr, heads);
        System.out.println(send);
    }
}
