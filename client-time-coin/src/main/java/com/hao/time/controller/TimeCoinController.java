package com.hao.time.controller;

import com.hao.common.controller.BaseSpringController;
import com.hao.common.pojo.ResponseData;
import com.hao.time.entity.TimeCoin;
import com.hao.time.service.TimeCoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/coin")
public class TimeCoinController extends BaseSpringController {
    @Autowired
    private TimeCoinService timeCoinService;

    @RequestMapping("addOrUpdate")
    public ResponseData<TimeCoin> addOrUpdateCoin(TimeCoin coin){
        ResponseData<TimeCoin> res = new ResponseData<>();
        timeCoinService.addOrUpdateCoin(coin);
        res.setMessage("操作成功");
        res.setCode(ResponseData.SUCCESS_CODE);
        return res;
    }

    @RequestMapping("getCoinData")
    public ResponseData<List<TimeCoin>> getCoinData(TimeCoin coin){
        ResponseData<List<TimeCoin>> res = new ResponseData<>();
        res.setData(timeCoinService.selectAll());
        res.setMessage("操作成功");
        res.setCode(ResponseData.SUCCESS_CODE);
        System.out.println(this.getUserId());
        return res;
    }


}
