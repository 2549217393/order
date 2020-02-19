package com.bdqn.order.service.impl;

import com.bdqn.order.mapper.PayMapper;
import com.bdqn.order.pojo.Order;
import com.bdqn.order.pojo.Pay;
import com.bdqn.order.pojo.User;
import com.bdqn.order.service.LoginService;
import com.bdqn.order.service.OrderService;
import com.bdqn.order.service.PayService;
import com.bdqn.order.util.constant.OprConstant;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class PayServiceImpl implements PayService {

    @Resource
    private PayMapper payMapper;

    @Resource
    private OrderService orderService;

    @Resource
    RabbitTemplate rabbitTemplate;


    @Resource
    private LoginService loginService;

    @Override
    public int addPayInfo(Pay pay) {
        return payMapper.insertSelective(pay);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> payAddResult(Integer id, HttpSession session) {
        Map<String,Object> map=new HashMap<>();
        Pay pay=new Pay();
        Order order=orderService.selectOrderById(id);
        Integer userId=(Integer)session.getAttribute("loginUser");
        User user=loginService.checkLoginUser(userId);
        if(order.getOrderStatus().equals("已超时")){
            map.put("error","该订单超时，已取消");
            return map;
        }
        if(order.getOrderStatus().equals("已支付")){
            map.put("error","你的订单已支付，无需支付");
            return map;
        }
        pay.setPayBefore(user.getUserBalance());//支付前账户余额
        if(loginService.reduceBalance(order)<1){
            //同步查询，同步扣除
            map.put("error","您的余额已不足，尚不能支付");
            return map;
        }
        pay.setOrderId(id);
        pay.setUserId(userId);
        pay.setPayAfter(user.getUserBalance().subtract(order.getOrderPrice()));//这个时候客户的钱已经扣除了
        if(addPayInfo(pay)>0){
            order.setOrderStatus("已支付");
            if(orderService.updateOrder(order)>0){
                map.put("result","success");
            }else{
                map.put("error","error");
            }
        }
        User oprUser=loginService.checkLoginUser((Integer) session.getAttribute("loginUser"));
        map.put(OprConstant.opr_action_user,oprUser);
        map.put(OprConstant.opr_action_type,OprConstant.opr_action_payCode);
        rabbitTemplate.convertAndSend("OperLogExchange","OperLogRouting", map);
        return map;
    }
}
