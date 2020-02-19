package com.bdqn.order.mapper;

import com.bdqn.order.pojo.Opr;

public interface OprMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Opr record);

    int insertSelective(Opr record);

    Opr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Opr record);

    int updateByPrimaryKey(Opr record);
}