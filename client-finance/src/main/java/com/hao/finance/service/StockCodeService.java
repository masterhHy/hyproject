package com.hao.finance.service;

import com.hao.finance.entity.SpliderStockCode;
import com.hao.finance.entity.po.StockCodeQuery;

import java.util.List;
import java.util.Map;

public interface StockCodeService extends BaseService<SpliderStockCode> {

    public List<Map<String,Object>> getCodeData(StockCodeQuery query);
}
