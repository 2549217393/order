package com.bdqn.order.service.impl;

import com.bdqn.order.mapper.GoodsMapper;
import com.bdqn.order.pojo.Goods;
import com.bdqn.order.service.GoodsService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;
    @Override
    public List<Goods> getGoodsList(Goods goods) {
        //查询所有商品
        PageHelper.startPage(goods.getPageNum(),goods.getPageSize());
        return goodsMapper.selectGoodsList(goods);
    }

    @Override
    public Goods getGoodsById(Integer id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int getAllCount(Goods goods) {
        return goodsMapper.selectGoodsCount(goods);
    }

    @Override
    public Map<String, Object> returnList(Goods goods) {
        Map<String,Object> map=new HashMap<>();
        List<Goods> goodsList =getGoodsList(goods);
        int total=getAllCount(goods);
        map.put("goodsList",goodsList);
        map.put("total",total);
        return map;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int reduceGoodsCount(Integer goodsId) {
        return goodsMapper.reduceCount(goodsId);
    }


}
