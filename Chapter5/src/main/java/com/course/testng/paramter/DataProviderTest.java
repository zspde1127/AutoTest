package com.course.testng.paramter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DataProviderTest {
    @Test(dataProvider = "data")
    public void testDataProvider(String name,int age){
        System.out.println("name = " + name +"  age = " + age);
    }

    @DataProvider(name = "data")
    public Object[][] providerData(){
        Object[][] objects = new Object[][]{
                {"zhangsan",10},
                {"lisi",20},
                {"wangwu",30}
        };
        return objects;
    }

    @Test(dataProvider = "methdData")
    public void test1(String name,int age){
        System.out.println("test1111 name = "+name+"  age = "+age);
    }

    @Test(dataProvider = "methdData")
    public void test2(String name,int age){
        System.out.println("test2222 name = "+name+"  age = "+age);
    }

    @DataProvider
    public Object[][] methdData(Method method){
        Object[][] obj = null;
        if (method.getName().equals("test1")){
            obj = new Object[][]{
                    {"zhangsan",1},
                    {"lisi",2},
                    {"wangwu",3}
            };

        }else if(method.getName().equals("test2")){
            obj = new Object[][]{
                    {"zhaoliu",99}
            };
        }
     return obj;
    }
}
