package com.course.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class GroupsOnClass1 {

    public void stu1(){
        System.out.println("1中的stu1允1111运行");
    }

    public void stu2(){
        System.out.println("1中的stu1允2222运行");
    }

}
