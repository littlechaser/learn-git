package com.test;

import com.google.common.collect.Maps;
import com.visionet.core.util.HttpClient;

import java.util.Map;

/**
 * Author   yang_tao@<yangtao.letzgo.com.cn>
 * Date     2017-12-28 23:02
 * Version  1.0
 */
public class TestJpush {
    public static void main(String[] args) {
        String authStr = "d118c60ebd830894fe42a6b9:ca4c76245fc06dde2961bf5d";//出租
//        String authStr = "ee24425e72509772cd542eca:4d8cae91c2f3630a8343cfea";//乘客
        Map<String, String> heads = Maps.newHashMap();
        heads.put("Content-Type", "application/json");
        heads.put("Authorization",
                "Basic " + org.apache.commons.codec.binary.Base64.encodeBase64String(authStr.getBytes()));
        String channelId = "1507bfd3f7f84b4e226";
//        //更新tag
////        String tags = "{\"tags\":{\"remove\":[\"1_2107\", \"1_0107\", \"1_2_107\", \"1_0_107\", \"1_1_107\", \"1_1107\"]}}";
////        HttpClient.https("https://device.jpush.cn/v3/devices/" + channelId, "POST", tags, heads);
//        //查tag
        String post1 = HttpClient.https("https://device.jpush.cn/v3/devices/" + channelId, "GET", null, heads);
        System.out.println(post1);
        //发推送
//        String pushStr = "{\"notification\":{\"android\":{\"alert\":\"行程已结束，订单总额120.00元，已支付100.00元，超出费用20.00元由乘车人支付，感谢使用\",\"extras\":{\"orderId\":\"20180108151505270000017\",\"type\":\"orderFinish\",\"content\":\"行程已结束，订单总额120.00元，已支付100.00元，超出费用20.00元由乘车人支付，感谢使用\"}},\"ios\":{\"alert\":\"行程已结束，订单总额120.00元，已支付100.00元，超出费用20.00元由乘车人支付，感谢使用\",\"extras\":{\"orderId\":\"20180108151505270000017\",\"type\":\"orderFinish\",\"content\":\"行程已结束，订单总额120.00元，已支付100.00元，超出费用20.00元由乘车人支付，感谢使用\"}}},\"audience\":{\"registration_id\":[\"" + channelId + "\"]},\"options\":{\"apns_production\":true,\"time_to_live\":6000,\"sendno\":1270216498},\"message\":{\"content_type\":\"json\",\"msg_content\":\"行程已结束，订单总额120.00元，已支付100.00元，超出费用20.00元由乘车人支付，感谢使用\",\"extras\":{\"orderId\":\"20180108151505270000017\",\"type\":\"orderFinish\",\"content\":\"行程已结束，订单总额120.00元，已支付100.00元，超出费用20.00元由乘车人支付，感谢使用\"}},\"platform\":\"all\"}";
//        String res = HttpClient.https("https://api.jpush.cn/v3/push", "POST", pushStr, heads);
//        System.out.println(res);
    }
}

