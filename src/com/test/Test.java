package com.test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Test {
    public static void main(String[] args) {
        System.out.println("新建一个简单的内存---------");
        List<String> list= new Vector<>();
        List<String> list1=Collections.synchronizedList(new ArrayList<>());
        List<String> list2=new CopyOnWriteArrayList<>();
        Map<Integer,Integer> map=new HashMap<>();
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                list.add(UUID.randomUUID().toString());
                System.out.println(UUID.randomUUID());
            }
        },"First-thread");
        System.out.println("开始多个线程------");
        for (int i=1;i<=20;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    list1.add(UUID.randomUUID().toString().substring(0,8));
                    //System.out.println(list);
                }
            },String.valueOf(i)).start();
        }
        for (String s : list1) {
            System.out.println(s);
        }
    }
}
