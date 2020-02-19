package com.bdqn.order.service;

import com.bdqn.order.pojo.Order;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface OrderService {
    int addOrder(Order order);

    List<Order> selectOrderByUserId(Integer id);

    Order selectOrderById(Integer id);

    int updateOrder(Order order);

    int cancelOrder();

    Map<String,Object> orderAddResult(Integer id, HttpSession session);
}
