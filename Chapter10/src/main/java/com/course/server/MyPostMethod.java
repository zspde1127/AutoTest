package com.course.server;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/",description = "这是我全部的post请求")
@RequestMapping("/v1")
public class MyPostMethod {

    //这个变量是用来装我们cookies信息的
    private static Cookie cookie;
    //用户登录成功获取到cookies，然后在访问其他接口获取到列表
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登录接口，成功后获取cookies信息",httpMethod = "POST")
    public String login(HttpServletResponse response, @RequestParam String userName,@RequestParam String
                        passWord){
        cookie = new Cookie("login","true");
        response.addCookie(cookie);
        if(userName.equals("zhangsan")&&passWord.equals("123456")){
            return "登录成功";
        }
        return "用户名或者密码错误";
    }

    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表",httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                              @RequestBody User u){
        User user;
        //获取cookies
        Cookie[] cookies = request.getCookies();
        //验证cookies是否合法
        for (Cookie c : cookies){
            //在jmeter中验证接口是出现值比对和地址比对的错误，因为前面用的equals，后面是用==
/*            if(c.getName()=="login"
                    &&c.getValue()=="true"
                    &&u.getUserName()=="zhangsan"
                    &&u.getPassWord()=="123456"){*/
            if(c.getName().equals("login")
                    &&c.getValue().equals("true")
                    && u.getUserName().equals("zhangsan")
                    && u.getPassWord().equals("123456")){
                user = new User();
                user.setName("lisi");
                user.setAge("18");
                user.setSex("man");
                return user.toString();
            }
        }
        return "参数不合法";
    }

}
