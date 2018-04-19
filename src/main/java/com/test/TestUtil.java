package com.test;

import com.visionet.core.util.DateUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Author   yang_tao@<yangtao.letzgo.com.cn>
 * Date     2017-11-13 13:19
 * Version  1.0
 */
public class TestUtil {
    public static void main(String[] args) {
//        System.out.println(AESUtil2.encode("80&18616393252&2019-10-01 12:00:00"));

        Person p = Person.builder()
                .name("a")
                .age(22)
                .sex(1)
                .birthDay(DateUtil.parseDate("2017-11-01 12:00:00"))
                .build();

        Person p2 = Person.builder()
                .name("b")
                .age(22)
                .sex(1)
                .birthDay(DateUtil.parseDate("2017-11-02 12:00:00"))
                .build();

        Person p3 = Person.builder()
                .name("c")
                .age(39)
                .sex(1)
                .birthDay(DateUtil.parseDate("2017-11-03 12:00:00"))
                .build();

        Person p4 = Person.builder()
                .name("d")
                .age(22)
                .sex(0)
                .birthDay(DateUtil.parseDate("2017-11-02 12:00:00"))
                .build();

        List<Person> list = new ArrayList<>();
        list.add(p);
        list.add(p2);
        list.add(p3);
        list.add(p4);

////        list.sort(Comparator.comparing(Person::getAge).reversed().thenComparing(Person::getBirthDay).thenComparing(Person::getSex));
////        list.forEach(item -> System.out.println(item.toString()));
////        list.parallelStream().forEach(item-> System.out.println(item.toString()));
////        list.stream().forEach(System.out::println);
////        list.stream().map(person -> person.getName()).collect(Collectors.toList());
////        list.stream().forEach(System.out::println);
//        List<Integer> ages = list.stream().map(person -> person.getAge() + 2).collect(Collectors.toList());
////        ages.stream().forEach(System.out::println);
////        List<Integer> ages2 = ages.stream().filter(integer -> integer == 24).collect(Collectors.toList());
////        ages2.stream().forEach(System.out::println);
//        ages.stream().peek(TestUtil::say).collect(Collectors.toSet());
//
//        Optional<Integer> max = ages.stream().max(Comparator.comparing(Integer::intValue));
//        System.out.println(max.get());
//        Optional<Integer> min = ages.stream().min(Comparator.comparing(Integer::intValue));
//        System.out.println(min.get());
//        Integer sum = ages.stream().reduce(0, Integer::sum);
//        System.out.println(sum);
//        int ave = list.stream().mapToInt(Person::getAge).sum();
//        int ave2 = ages.stream().mapToInt(Integer::intValue).sum();
////        Integer ave3 = ages.stream().mapToInt(Integer::intValue).sum();
//        double ave3 = ages.stream().collect(Collectors.averagingInt(Integer::intValue));
//        OptionalDouble ave4 = ages.stream().mapToInt(Integer::intValue).average();
//        System.out.println(ave);
//        System.out.println(ave2);
////        System.out.println(ave3);
//        System.out.println(ave4.getAsDouble());

        final Map<Integer, List<Person>> collect = list.stream().collect(Collectors.groupingBy(Person::getAge));
        final List<Person> people = collect.get(22);
        final List<Person> people2 = collect.get(39);
        final Set<Integer> keySet = collect.keySet();
        keySet.forEach(System.out::println);

        Integer[] a = new Integer[]{17,18,19,20,21,22,6,25,26,27,28,31,32,33,38,51,52};
        Integer[] b = new Integer[]{6,25,26,27,28,31,32,33,38,51,52,53,54};
        List<Integer> as = Arrays.asList(a);
        List<Integer> bs = Arrays.asList(b);
        List<Integer> all = new ArrayList<>();
        all.addAll(as);
        all.addAll(bs);
        final List<Integer> collect1 = all.stream().distinct().sorted().collect(Collectors.toList());
        collect1.forEach(item -> System.out.print(item + ","));

    }

    public static void say(Object o) {
        System.out.println(o.toString());
    }
}
