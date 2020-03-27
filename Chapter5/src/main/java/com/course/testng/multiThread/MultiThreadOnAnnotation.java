package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class MultiThreadOnAnnotation {

    /**
     * 多线程测试，没有关联的用例可以使用多线程，减少执行时间
     * 下面演示3个线程同时运行，共执行10次
     */
    @Test(invocationCount = 10,threadPoolSize = 3)
    public void multiTreadTest(){
        System.out.println("运行线程测试");
        System.out.println("线程ID： "+Thread.currentThread().getId());
    }
}
