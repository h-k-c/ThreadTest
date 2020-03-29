package com.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo {
    AtomicReference<Thread> atomicReference=new AtomicReference<>();
    public void mylock(){
        Thread thread=Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"come in this thread");
        while (!atomicReference.compareAndSet(null,thread)){

        }
    }
    public void myunlock(){
       Thread thread=Thread.currentThread(); 
       atomicReference.compareAndSet(thread,null);
       System.out.println(Thread.currentThread().getName()+"已经解锁-----------");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo=new SpinLockDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                spinLockDemo.mylock();
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                spinLockDemo.myunlock();
            }
        },"AA").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                spinLockDemo.mylock();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                spinLockDemo.myunlock();
            }
        },"BB").start();
    }
}
