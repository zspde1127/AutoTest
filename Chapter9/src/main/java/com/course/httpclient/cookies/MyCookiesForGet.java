package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.DefaultClientConnection;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet {

    private String url;
    private ResourceBundle bundle;
    private CookieStore cookieStore;

    @BeforeTest
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }

    @Test
    public void testGetCookies() throws IOException {
        String result;
        //从配置文件中拼接测试url
        String test_url = this.url;
        String test_uri = bundle.getString("getCookies.uri");

        //测试逻辑代码书写
        HttpGet httpGet =new HttpGet(test_url + test_uri);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(httpGet);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        //获取cookies信息
        this.cookieStore = client.getCookieStore();
        List<Cookie> cookieList = cookieStore.getCookies();

        for (Cookie cookie : cookieList) {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("Cookie name = " + name
            + " ; Cookie value = " + value);
        }
    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void testGetWithCookies() throws IOException {
        String test_uri2 = bundle.getString("test.getwithCookies.uri");
        String test_url2 = this.url+test_uri2;

        HttpGet httpGet = new HttpGet(test_url2);
//        HttpClient httpClient = HttpClientBuilder.create().build();//获取DefaultHttpClient请求
        DefaultHttpClient httpClient = new DefaultHttpClient();

        //设置cookies信息
        httpClient.setCookieStore(this.cookieStore);
        HttpResponse httpResponse = httpClient.execute(httpGet);

        //获取响应的状态码
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println("statusCode = " + statusCode);

        if (statusCode == 200){
            String result = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
            System.out.println(result);
        }

    }



}
