package com.hao.time.controller;

import com.hao.common.pojo.ResponseData;
import com.hao.time.entity.TimeCoin;
import com.hao.time.service.TimeCoinService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/coin")
public class TimeCoinController {
    private Logger logger = LoggerFactory.getLogger(TimeCoinController.class);
    @Autowired
    private TimeCoinService timeCoinService;

    @RequestMapping("addOrUpdate")
    public ResponseData<TimeCoin> addOrUpdateCoin(TimeCoin coin){
        ResponseData<TimeCoin> res = new ResponseData<>();


        try {
            timeCoinService.addOrUpdateCoin(coin);
            res.setMessage("操作成功");
            res.setCode(200);
        } catch (Exception e) {
            logger.error("",e);
            res.setCode(500);

        }
        return res;
    }

    @RequestMapping("getCoinData")
    public ResponseData<List<TimeCoin>> getCoinData(TimeCoin coin){
        ResponseData<List<TimeCoin>> res = new ResponseData<>();


        try {
            res.setData(timeCoinService.selectAll());
            res.setMessage("操作成功");
            res.setCode(200);
        } catch (Exception e) {
            logger.error("",e);
            res.setCode(500);

        }
        return res;
    }


}
