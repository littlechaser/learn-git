package com.test;

import com.alibaba.fastjson.JSONObject;
import com.visionet.core.util.DecimalUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author yang_tao@<yangtao.letzgo.com.cn>
 * @version 1.0
 * @date 2018-03-09 16:30
 */
public class Tools {
    public static void main(String[] args) throws IOException {
        Map<String, Integer> citys = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\cbm\\Desktop\\已开通城市.txt"))) {
            String temp;
            while ((temp = reader.readLine()) != null) {
                String[] split = temp.split("\\s+");
                citys.put(split[1], Integer.parseInt(split[0]));
            }
        }
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\cbm\\Desktop\\字典表.txt"))) {
            List<JSONObject> list = new ArrayList<>();
            String temp;
            while ((temp = reader.readLine()) != null) {
                list.add(JSONObject.parseObject(temp));
            }
//            Optional<JSONObject> city = list.stream().filter(
//                    item -> "香港特别行政区".equals(item.getString("city")) &&
//                            "0".equals(item.getString("orderType")) &&
//                            "舒适型".equals(item.getString("type"))
//            ).findFirst();
//            if (city.isPresent()) {
//                JSONObject jsonObject = city.get();
//                System.out.println("startPrice：" + jsonObject.getString("startPrice"));
//                System.out.println("unitPrice：" + jsonObject.getString("unitPrice"));
//                System.out.println("minitePrice：" + jsonObject.getString("minitePrice"));
//                System.out.println("overDistance：" + jsonObject.getString("overDistance"));
//                System.out.println("overDistanceUnitPrice：" + jsonObject.getString("overDistanceUnitPrice"));
//            }
            list.sort(Comparator.comparing(jsonObject -> jsonObject.getString("city")));
            list.forEach(item -> {
                try {
                    Integer cityId = citys.get(item.getString("city"));
                    Integer carType = item.getInteger("carType");
                    Integer orderType = item.getInteger("orderType");
                    double overDistance = DecimalUtil.round(item.getDouble("overDistance"));
                    double overDistanceUnitPrice = DecimalUtil.round(item.getDouble("overDistanceUnitPrice"));
                    System.out.println("UPDATE t_charge_standard SET overDistance=" + overDistance + ",overDistanceUnitPrice=" + overDistanceUnitPrice +
                            " WHERE city_id=" + cityId + " AND car_type=" + carType + " AND orderType=" + orderType + ";");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(item.toJSONString());
                    return;
                }
            });
        }
    }
}
