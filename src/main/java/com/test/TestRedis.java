package com.test;

import com.visionet.core.redis.RedisUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author yang_tao@<yangtao.letzgo.com.cn>
 * @version 1.0
 * @date 2018-03-02 17:54
 */
public class TestRedis {
    public static void main(String[] args) throws ParseException {
        Person p = Person.builder()
                .name("allen")
                .sex(1)
                .birthDay(new SimpleDateFormat("yyyy-MM-dd").parse("1993-07-02"))
                .age(25)
                .build();
        RedisUtil.setObjectData("person-object", p, 30);
        Object person = RedisUtil.getObjectData("person-object");
        if (person != null) {
            System.out.println(person.toString());
        }
        System.out.println("========================");
        RedisUtil.setObjectListData("person-list", p, 60);
        List<Object> objectListData = RedisUtil.getObjectListData("person-list");
        for (int i = 0; objectListData != null && !objectListData.isEmpty() && i < objectListData.size(); i++) {
            System.out.println(objectListData.get(i).toString());
        }
    }
}
