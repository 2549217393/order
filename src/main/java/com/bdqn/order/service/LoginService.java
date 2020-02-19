package com.bdqn.order.service;

import com.bdqn.order.pojo.Order;
import com.bdqn.order.pojo.User;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Map;

/**
 * 登录service
 */
public interface LoginService {
    //登录功能
    public User loginUser(User user);

    /**
     * 注册功能
     * @param user
     * @return
     */
    public int registerUser(User user);

    /**
     * 检查是否登录
     * @param id
     * @return
     */
    public User checkLoginUser(Integer id);

    /**
     * 减少用户余额
     * @param order
     * @return
     */
    public int reduceBalance(Order order);

    /**
     * 返回controller 登录结果
     * @param user
     * @param session
     * @return
     */
    Map<String ,Object> login(User user, HttpSession session);

    /**
     * 返回controller注册结果
     * @param user
     * @return
     */
    Map Register(User user);

}
