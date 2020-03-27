package com.course.testng.suite;

import org.testng.annotations.Test;

public class ExpectedException {
    /**
     * 什么时候会用到异常测试？
     *在我们期望结果为某一个异常的时候
     * 比如：我们传入了某些不合法的参数，数据抛出了异常
     * 也就是说我的预期结果就是这个异常
     */
    //这就是一个测试结果会失败的异常测试

    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionFailed(){
        System.out.println("这是一个失败的异常测试");

    }

    //这是一个成功的异常测试

    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionSuccess(){
        System.out.println("这是我的一次异常测试");
        throw new RuntimeException();
    }
}
