package com.course.testng.groups;


import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupsOnMethod {

    @Test(groups="server")
    public void test1(){
        System.out.println("这是服务端组的测试方法11111");
    }

    @Test(groups= "server")
    public void test2(){
        System.out.println("这是服务端组的测试方法22222");
    }

    @Test(groups= "client")
    public void test3(){
        System.out.println("这是客户端测试方法11111");
    }

    @Test(groups= "client")
    public void test4(){
        System.out.println("这是客户端测试方法22222");
    }

    @BeforeGroups("server")
    public void test5(){
        System.out.println("这是服务端运行之前的方法");
    }

    @AfterGroups("server")
    public void test6(){
        System.out.println("这是服务端运行之后的方法");
    }
}
