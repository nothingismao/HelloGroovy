//package com.cn.m.base.collection;
//
//import java.util.HashMap;
//import java.util.concurrent.*;
//import java.util.concurrent.atomic.AtomicInteger;
//
///**
// * Created by macx on 13/2/14.
// */
//public class HashMapConcurrentTest {
//    private static HashMap map = new HashMap();
//    private Executor executor = null;
//
//    public HashMapConcurrentTest() {
////        executor = Executors.newCachedThreadPool(new ThreadFactory() {
////            private AtomicInteger index = new AtomicInteger(0);
////
////            @Override
////            public Thread newThread(Runnable r) {
////                Thread t = new Thread(r, "HashMapThreadPool-" + index.incrementAndGet());
////                return t;
////            }
////        });
//    }
//
//    public static void main(String[] args) throws InterruptedException {
//        System.out.printf("Start");
//        TimeUnit.SECONDS.sleep(10);
////        HashMapConcurrentTest test = new HashMapConcurrentTest();
////        test.executor.execute(new HashAddThread());
////        test.executor.execute(new HashAddThread());
////        test.executor.execute(new HashAddThread());
//        new Thread(new HashAddThread()).start();
//        new Thread(new HashAddThread()).start();
//        new Thread(new HashAddThread()).start();
//
//        System.out.println("End");
//
//    }
//
//
//    static class HashAddThread implements Runnable {
//        public void run() {
//            for (int i = 0; i < 1000000000; i++) {
//                map.put(new Integer(i), new Integer(i));
//            }
//            System.out.printf("Thread Over!");
//        }
//    }
//
//}
