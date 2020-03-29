package com.test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {
    public static void main(String[] args){
        Semaphore semaphore=new Semaphore(3);
        for(int i=1;i<=6;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName()+"抢车位=========");
                        TimeUnit.SECONDS.sleep(2);
                        System.out.println(Thread.currentThread().getName()+"============离开停车位");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        semaphore.release();
                    }
                }
            }).start();
        }
    }
}
