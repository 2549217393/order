package com.bdqn.order.service;

import com.bdqn.order.pojo.Pay;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface PayService {
    int addPayInfo(Pay pay);

    Map<String,Object> payAddResult(Integer id, HttpSession session);
}
