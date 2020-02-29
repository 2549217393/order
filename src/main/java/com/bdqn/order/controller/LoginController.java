package com.bdqn.order.controller;

import com.bdqn.order.pojo.User;
import com.bdqn.order.service.LoginService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class LoginController {

    @Resource
    private LoginService loginService;

    @Resource
    private DefaultKaptcha defaultKaptcha;


    /**
     * 登录操作
     * @param user
     * @return
     */
    @RequestMapping("/login")
    public Object login(@RequestBody User user,HttpSession session)
    {
        String rightCode=(String) session.getAttribute("rightCode");
        Map<String,Object> map=new HashMap<>();
        if(rightCode==null||!rightCode.equals(user.getUserCode())){
            map.put("error","验证码错误");
            return map;
        }
        map=loginService.login(user,session);
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

    @RequestMapping("/img")
    public void img(HttpServletRequest request, HttpServletResponse httpServletResponse){

        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            // 生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            request.getSession().setAttribute("rightCode", createText);
            // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);

            // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
            captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
            httpServletResponse.setHeader("Cache-Control", "no-store");
            httpServletResponse.setHeader("Pragma", "no-cache");
            httpServletResponse.setDateHeader("Expires", 0);
            httpServletResponse.setContentType("image/jpeg");
            ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
            responseOutputStream.write(captchaChallengeAsJpeg);
            responseOutputStream.flush();
            responseOutputStream.close();

        } catch (IllegalArgumentException e) {
            return;
        } catch (IOException e) {
            return;
        }

    }

}
