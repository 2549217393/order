package com.bdqn.order.controller;

import com.bdqn.order.pojo.Goods;
import com.bdqn.order.pojo.Order;
import com.bdqn.order.pojo.Pay;
import com.bdqn.order.pojo.User;
import com.bdqn.order.service.GoodsService;
import com.bdqn.order.service.LoginService;
import com.bdqn.order.service.OrderService;
import com.bdqn.order.service.PayService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/order/product")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    @Resource
    private OrderService orderService;

    @Resource
    private LoginService loginService;

    @Resource
    private PayService payService;

    @RequestMapping("/list")
    public Object selectAllGoods(Goods goods){
        Map<String,Object> map=goodsService.returnList(goods);
        return map;
    }

    @GetMapping(value = "/{id}")
    public Object getInfo(@PathVariable Integer id)
    {
        Goods goods=goodsService.getGoodsById(id);
        Map map=new HashMap();
        map.put("info",goods.getGoodsInfo());
        return map;
    }

    /**
     *
     * @param id 商品id
     * @param session
     * @return
     */
    @RequestMapping(value = "/addOrder/{id}")
    public Object addOrder(@PathVariable Integer id,HttpSession session){
        Map<String,Object> map=orderService.orderAddResult(id,session);
        return map;
    }

    @RequestMapping("/allOrder")
    public Object selectAllOrder(HttpSession session){
        Map<String,Object> map=new HashMap<>();
        Integer id=(Integer) session.getAttribute("loginUser");
        List<Order> orderList=orderService.selectOrderByUserId(id);
        map.put("orderList",orderList);
        return map;
    }

    /**
     *
     * @param id 订单id
     * @param session
     * @return
     */
    @RequestMapping(value = "/pay/{id}")
    public Object payOrder(@PathVariable Integer id,HttpSession session){
        Map<String,Object> map=payService.payAddResult(id,session);
        return map;
    }
}
