//package com.test;
//
//import com.visionet.core.util.PikaUtil;
//import com.visionet.user.dome.CarWorkDto;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Set;
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.LinkedBlockingDeque;
//import java.util.concurrent.atomic.AtomicLong;
//
///**
// * @author yang_tao@<yangtao.letzgo.com.cn>
// * @version 1.0
// * @date 2018-03-28 12:15
// */
//public class AddGps implements Runnable {
//
//    private AtomicLong count = new AtomicLong();
//    private List<CarWorkDto> carWorkDtos = new ArrayList<>();
//    private List<String> cars = new ArrayList<>();
//    private BlockingQueue<Integer> taskNumber = new LinkedBlockingDeque<>();
//    private Integer threadNum;
//
//    ExecutorService executor;
//
//    public AddGps(int cars, int taskNum, int threadNum) {
//        executor = Executors.newFixedThreadPool(threadNum);
//        this.threadNum = taskNum;
//        for (int i = 0; i < cars; i++) {
//            CarWorkDto carWorkDto = new CarWorkDto();
//            carWorkDto.setBusinessType(0);
//            carWorkDto.setIsWork(1);
//            carWorkDto.setLon("121.24235");
//            carWorkDto.setLat("31.35261");
//            carWorkDto.setCid(i + 1);
//            carWorkDto.setPhone(String.valueOf(i + 1));
//            carWorkDto.setDate(new Date());
//            carWorkDtos.add(carWorkDto);
//        }
//        for (int i = 0; i < taskNum; i++) {
//            try {
//                taskNumber.put(i);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    public void run() {
//        while (true) {
//            Integer currentTimes = taskNumber.poll();
//            if (currentTimes == null) {
//                System.out.println("add end");
//                break;
//            }
//            carWorkDtos.forEach(item -> {
//                PikaUtil.addToCarGpsList("107_carWork^" + item.getPhone() + "^2018-04-10", item);
//                count.addAndGet(1);
//            });
//        }
//    }
//
//    public long getCount() {
//        return count.get();
//    }
//
//    public void start() {
//        for (int i = 0; i < this.threadNum; i++) {
//            executor.execute(this);
//        }
//    }
//
//    public static void main(String[] args) {
////        AddGps addGps = new AddGps(10000, 3000, 300);
////        addGps.start();
//        List<CarWorkDto> carGpsList = PikaUtil.getCarGpsList("383_carwork^13967365096^2018-04-15");
//        System.out.println(carGpsList.size());
//        System.out.println(carGpsList);
//    }
//
//}
