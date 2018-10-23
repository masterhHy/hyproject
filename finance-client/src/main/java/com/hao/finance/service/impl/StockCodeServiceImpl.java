package com.hao.finance.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hao.finance.dao.SpliderStockCodeMapper;
import com.hao.finance.entity.SpliderStockCode;
import com.hao.finance.entity.po.StockCodeQuery;
import com.hao.finance.service.StockCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StockCodeServiceImpl extends BaseServiceImpl<SpliderStockCode> implements StockCodeService {

    @Autowired
    private SpliderStockCodeMapper stockCodeMapper;

    @Override
    public List<Map<String, Object>> getCodeData(StockCodeQuery query) {
        PageHelper.startPage(query.getPageNum(),query.getPageSize(),false);
        List<Map<String, Object>> codeData = stockCodeMapper.getCodeData(query);
        PageInfo<Map<String,Object>> page = new PageInfo<>(codeData);
        return page.getList();
    }
}
