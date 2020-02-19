package com.bdqn.order.service.impl;

import com.bdqn.order.mapper.OprMapper;
import com.bdqn.order.pojo.Opr;
import com.bdqn.order.pojo.User;
import com.bdqn.order.service.OprService;
import com.bdqn.order.util.constant.OprConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

@Service
@RabbitListener(queues = "operLogQueue")
public class OprServiceImpl implements OprService {

    @Resource
    private OprMapper oprMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOpr(Opr opr) {
        //将日志存入库中
        oprMapper.insertSelective(opr);
    }

    /**
     * 用户消费
     * @param map
     */
    @RabbitHandler
    public void addMqOprLog(Map map) {
        User u = (User) map.get(OprConstant.opr_action_user);
        Opr logOpr=new Opr();
        if(u==null){
            logOpr.setOprName(OprConstant.opr_login_null);
        }else{
            logOpr.setOprName(u.getUserName());
        }

        String oprType=(String)map.get(OprConstant.opr_action_type);

        if(oprType.equals(OprConstant.opr_action_loginCode)){
            logOpr.setOprType(OprConstant.opr_action_login);
        }

        if(oprType.equals(OprConstant.opr_action_payCode)){
            logOpr.setOprType(OprConstant.opr_action_pay);
        }

        if(oprType.equals(OprConstant.opr_action_orderCode)){
            logOpr.setOprType(OprConstant.opr_action_order);
        }

        saveOpr(logOpr);
    }
}
