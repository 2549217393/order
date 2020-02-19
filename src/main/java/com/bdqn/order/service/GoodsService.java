package com.bdqn.order.service;

import com.bdqn.order.pojo.Goods;

import java.util.List;
import java.util.Map;

public interface GoodsService {
     /**
      * 查询所有的商品
      * @param goods
      * @return
      */
     List<Goods> getGoodsList(Goods goods);

     /**
      * 根据商品编号获取商品
      * @param id
      * @return
      */
     Goods getGoodsById(Integer id);

     /**
      * 获取商品总数
      * @param goods
      * @return
      */
     int getAllCount(Goods goods);

     /**
      * 返回controller查询所有的商品
      * @param goods
      * @return
      */
     Map<String,Object> returnList(Goods goods);

     /**
      * 减少商品总数
      * @param goodsId
      * @return
      */
     int reduceGoodsCount(Integer goodsId);
}
