package com.test;

import jdk.nashorn.internal.ir.Block;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadAndWriter {
    private volatile Map<String,Object> map=new HashMap<>();
    private ReentrantReadWriteLock rwLock=new ReentrantReadWriteLock();
    public void put(String key,Object value){
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"开始写入========="+key);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入完成===============");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rwLock.writeLock().unlock();
        }

    }
    public void get(String key){
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"开始读入==========="+key);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object result=map.get(key);
            System.out.println(Thread.currentThread().getName()+"结束读入==========");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rwLock.readLock().unlock();
        }

    }

    public static void main(String[] args) {
        ReadAndWriter readAndWriter=new ReadAndWriter();
        for (int i=1;i<=5;i++){
            final int tmp=i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    readAndWriter.put(tmp+"",tmp+"");
                }
            },String.valueOf(i)).start();
        }

        for (int i=1;i<=5;i++){
            final int tmp=i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    readAndWriter.get(tmp+"");
                }
            },String.valueOf(i)).start();
        }
    }
}
