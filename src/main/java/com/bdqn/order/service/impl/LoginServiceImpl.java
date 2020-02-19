package com.bdqn.order.service.impl;

import com.bdqn.order.mapper.UserMapper;
import com.bdqn.order.pojo.Order;
import com.bdqn.order.pojo.User;
import com.bdqn.order.service.LoginService;
import com.bdqn.order.util.Md5Utils;
import com.bdqn.order.util.constant.OprConstant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserMapper userMapper;

    @Resource
    RabbitTemplate rabbitTemplate;

    @Override
    public User loginUser(User user) {
        //登录方法实现
        return userMapper.selectUserByName(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int registerUser(User user) {
        //注册方法实现
        return userMapper.insertSelective(user);
    }

    @Override
    public User checkLoginUser(Integer id){
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int reduceBalance(Order order) {
        return userMapper.reduceMoney(order);
    }

    @Override
    public Map<String, Object> login(User user, HttpSession session) {
        Map<String,Object> map=new HashMap<>();
        if(user.getUserPwd()==null||user.getUserName()==null){
            map.put("error","请完善信息");
            return map;
        }
        Subject subject= SecurityUtils.getSubject();
        user.setUserPwd(Md5Utils.hash(user.getUserPwd()));
        UsernamePasswordToken token=new UsernamePasswordToken(user.getUserName(),user.getUserPwd());
        try {
            subject.login(token);
        }catch (UnknownAccountException un){
            map.put("error","用户不存在");
            return map;
        }catch (IncorrectCredentialsException in){
            map.put("error","密码错误");
            return map;
        }
        User user_ = (User) SecurityUtils.getSubject().getPrincipal();
        session.setAttribute("loginUser",user_.getUserId());
        map.put("result","true");
        map.put(OprConstant.opr_action_user,user_);
        map.put(OprConstant.opr_action_type,OprConstant.opr_action_loginCode);
        rabbitTemplate.convertAndSend("OperLogExchange","OperLogRouting", map);
        return map;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Map Register(User user) {
        Map map=new HashMap();
        if(user.getUserPwd()==null||user.getUserName()==null){
            map.put("error","请完善信息");
            return map;
        }
        User loginUser=loginUser(user);
        if(loginUser!=null){
            map.put("error","用户已存在");
            return map;
        }
        user.setUserBalance(BigDecimal.valueOf(1000));
        user.setUserPwd(Md5Utils.hash(user.getUserPwd()));
        if(registerUser(user)>0){
            map.put("success","true");
        }else{
            map.put("error","注册失败");
        }
        return map;
    }


}
