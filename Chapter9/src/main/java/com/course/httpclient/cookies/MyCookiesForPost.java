package com.course.httpclient.cookies;

import com.sun.deploy.nativesandbox.comm.Response;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {

    private ResourceBundle bundle;
    private String url;
    private CookieStore cookieStore;

    @BeforeTest
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("post_application", Locale.CHINA);
        url = bundle.getString("test_post.url");
    }

    @Test
    public void getCookiesTest() throws IOException {
        String result;
        //拼接url
        String post_test_uri = bundle.getString("getCookies.uri");
        String test_url = this.url+post_test_uri;

        //执行逻辑代码
        HttpGet httpGet = new HttpGet(test_url);
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(httpGet);

        result = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
        System.out.println(result);

        //获取cookie信息
        cookieStore = httpClient.getCookieStore();
        List<Cookie> cookiesList = cookieStore.getCookies();

        for(Cookie cookie : cookiesList){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("Cookie name = " + name
                    + " ; Cookie value = " + value);
        }
    }

    @Test(dependsOnMethods = {"getCookiesTest"})
    public void postWithCookiesMethod(){
        String post_test_uri = bundle.getString("post_test_cookieswithCookies.uri");
        //拼接最终的测试地址
        String post_test_url = post_test_uri+this.url;
        //声明一个client对象，用来进行方法的执行

        //声明一个方法，这个方法就是post方法

        //添加参数

        //设置请求头信息

    }

}
