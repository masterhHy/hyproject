package com.hao.finance.controller;

import com.hao.common.pojo.ResponseData;
import com.hao.finance.entity.po.StockCodeQuery;
import com.hao.finance.service.StockCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/code")
public class StockCodeController {

    @Autowired
    private StockCodeService stockCodeService;

    @RequestMapping("getCodeData")
    public ResponseData<List<Map<String, Object>>> getCodeData(StockCodeQuery query){
        ResponseData<List<Map<String, Object>>> res = new ResponseData<>();
        List<Map<String, Object>> codeData = stockCodeService.getCodeData(query);
        res.setCode(ResponseData.SUCCESS_CODE);
        res.setData(codeData);
        return res;
    }


}
