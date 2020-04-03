package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是我全部的get方法")
public class MyGetMethod {
    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
    @ApiOperation(value = "通过这个方法可以获取cookies",httpMethod = "GET")
    public String getCookies(HttpServletResponse response){
        //HttpServerletRequest 装请求信息的类
        //HttpServerletResponse 装响应信息的类
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "恭喜你获得cookies信息成功";
    }

    /**
     * 要求客户端携带cookies访问
     * 这是一个需要携带cookies信息才能访问的get请求
     */

    @RequestMapping(value = "/getwithCookies",method = RequestMethod.GET)
    @ApiOperation(value = "要求客户端携带cookies访问",httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)){
            return "你必须携带cookies信息来";
        }
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("login")&&cookie.getValue().equals("true")){
                return "这是一个需要携带cookies信息才能访问的get请求";
            }
        }
        return "你必须携带cookies信息来";
    }

    /**
     * 开发一个需要携带参数才能访问的get请求
     * 第一种实现方式 url: key=value&key=value  例如  http://localhost:8888/getwithparam?start=1&end=10
     * 我们来模拟获取商品列表
     */

    @RequestMapping(value = "/getwithparam",method = RequestMethod.GET)
    @ApiOperation(value = "需要携带参数才能访问的get请求",httpMethod = "GET")
    public Map<String,Integer> getList(@RequestParam Integer start,@RequestParam Integer end){
        Map<String,Integer> shangpingMap = new HashMap<>();
        shangpingMap.put("鞋子",400);
        shangpingMap.put("衬衫",300);
        shangpingMap.put("干脆面",1);

        return shangpingMap;
    }

    /**
     * 第二种携带参数才能访问的get请求
     * url：ip：port/getwithparam/1/10  例如  http://localhost:8888/getwithparam/1/10
     */

    @RequestMapping(value = "/getwithparam/{start}/{end}")
    @ApiOperation(value = "第二种携带参数才能访问的get请求",httpMethod = "GET")
    public Map getList_2(@PathVariable Integer start,@PathVariable Integer end){
        Map<String,Integer> shangpingMap_2 = new HashMap<>();
        shangpingMap_2.put("鞋子",400);
        shangpingMap_2.put("衬衫",300);
        shangpingMap_2.put("干脆面",1);

        return shangpingMap_2;

    }
}
