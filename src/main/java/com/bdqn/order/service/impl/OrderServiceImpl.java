package com.bdqn.order.service.impl;

import com.bdqn.order.mapper.OrderMapper;
import com.bdqn.order.pojo.Goods;
import com.bdqn.order.pojo.Order;
import com.bdqn.order.pojo.User;
import com.bdqn.order.service.GoodsService;
import com.bdqn.order.service.LoginService;
import com.bdqn.order.service.OrderService;
import com.bdqn.order.util.constant.OprConstant;
import com.bdqn.order.util.redis.RedisUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private GoodsService goodsService;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    RabbitTemplate rabbitTemplate;

    @Resource
    private OrderService orderService;

    @Resource
    private LoginService loginService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int addOrder(Order order) {
        return orderMapper.insertSelective(order);
    }

    @Override
    public List<Order> selectOrderByUserId(Integer id) {
        return orderMapper.selectAllOrder(id);
    }

    @Override
    public Order selectOrderById(Integer id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateOrder(Order order) {
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public int cancelOrder() {
        return orderMapper.cancelOrder();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> orderAddResult(Integer id, HttpSession session) {
        Boolean getLock = redisUtil.getLock(id+"_addOrder", 10 * 1000);
        Map<String,Object> map=new HashMap<>();
        if(getLock==false){
            map.put("error","前面还有人在购买，请排队等候");
            return map;
        }
        if(goodsService.reduceGoodsCount(id)>0){
            Goods goods =goodsService.getGoodsById(id);
            Order order=new Order();
            order.setGoodsId(goods.getGoodsId());
            order.setOrderPrice(goods.getGoodsPrice());
            order.setUserId((Integer) session.getAttribute("loginUser"));
            order.setOrderStatus("未支付");
            if(orderService.addOrder(order)>0){
                map.put("result","success");
            }
        }else{
            map.put("error","哎呀，来晚一步了，商品抢光了");
        }
        redisUtil.releaseLock(id+"_addOrder");
        User oprUser=loginService.checkLoginUser((Integer) session.getAttribute("loginUser"));
        map.put(OprConstant.opr_action_user,oprUser);
        map.put(OprConstant.opr_action_type,OprConstant.opr_action_orderCode);
        rabbitTemplate.convertAndSend("OperLogExchange","OperLogRouting", map);
        return map;
    }
}
