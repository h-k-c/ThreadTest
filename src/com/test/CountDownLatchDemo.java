package com.test;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(6);
        for(int i=1;i<=6;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"国==============i:");
                    countDownLatch.countDown();
                }
            },CountryEnum.for_eachCountryEnum(i).getRetMessage()).start();
        }
      //  countDownLatch.await();
    }
}
