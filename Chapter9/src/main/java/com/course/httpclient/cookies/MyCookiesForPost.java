package com.course.httpclient.cookies;

import com.sun.deploy.nativesandbox.comm.Response;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
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
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
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
    public void postWithCookiesMethod() throws IOException {
        String post_test_uri = bundle.getString("post_test_cookieswithCookies.uri");
        //拼接最终的测试地址
        String post_test_url = post_test_uri+this.url;
        //声明一个client对象，用来进行方法的执行
        DefaultHttpClient httpClient = new DefaultHttpClient();
        //声明一个方法，这个方法就是post方法
        HttpPost httpPost = new HttpPost(post_test_url);
        //添加参数
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","zhangsan");
        jsonObject.put("age","18");
        //设置请求头信息
        httpPost.setHeader("content-type","application/json");
        //将参数添加到方法中
        StringEntity stringEntity = new StringEntity(jsonObject.toString(),"utf-8");
        httpPost.setEntity(stringEntity);
        //声明一个对象来存储响应结果
        String result;
        //设置cookie信息
        httpClient.setCookieStore(this.cookieStore);
        //执行post方法
        HttpResponse httpResponse = httpClient.execute(httpPost);
        //获取响应结果
        result = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
        System.out.println(result);
        //处理结果判断是否符合预期
        //蒋返回的响应结果转换成json对象
        JSONObject resultJson = new JSONObject(result);
        //获取到结果值
        String success = (String) resultJson.get("zhangsan");
        String statusCode = (String) resultJson.get("status");
        //具体的判断返回结果的值
        Assert.assertEquals("success",success);
        Assert.assertEquals("1",statusCode);
    }

}
