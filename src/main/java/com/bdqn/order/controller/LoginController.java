package com.bdqn.order.controller;

import com.bdqn.order.pojo.User;
import com.bdqn.order.service.LoginService;
import com.bdqn.order.util.Md5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 登录操作
     * @param user
     * @return
     */
    @RequestMapping("/login")
    public Object login(@RequestBody User user,HttpSession session)
    {
        Map<String,Object> map=loginService.login(user,session);
        return map;
    }

    /**
     * 注册操作
     * @param user
     * @return
     */
    @RequestMapping("/register")
    public Object register(@RequestBody User user)
    {
        Map map=loginService.Register(user);
        return map;
    }

    /**
     * 无权限Shiro返回路径
     * @return
     */
    @RequestMapping("/toLogin")
    public Object toLogin(){
        Map map=new HashMap();
        map.put("error","null");
        return map;
    }
}
