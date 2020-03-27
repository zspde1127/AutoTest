package com.course.testng;

import org.testng.annotations.Test;

public class DependTest {
    @Test
    public void dependTest1(){
        System.out.println("1run");
        throw new RuntimeException();
    }

    @Test(dependsOnMethods = {"dependTest1"})
    public void dependTest2(){
        System.out.println("2run");
    }
}
