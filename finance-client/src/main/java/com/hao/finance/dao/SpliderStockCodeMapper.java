package com.hao.finance.dao;

import com.hao.finance.entity.SpliderStockCode;
import com.hao.finance.entity.po.StockCodeQuery;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface SpliderStockCodeMapper extends Mapper<SpliderStockCode> {

    public List<Map<String,Object>> getCodeData(@Param("query")StockCodeQuery query);
}