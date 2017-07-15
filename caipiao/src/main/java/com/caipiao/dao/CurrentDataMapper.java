package com.caipiao.dao;

import com.caipiao.pojo.CurrentData;

public interface CurrentDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CurrentData record);

    int insertSelective(CurrentData record);

    CurrentData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CurrentData record);

    int updateByPrimaryKey(CurrentData record);
}