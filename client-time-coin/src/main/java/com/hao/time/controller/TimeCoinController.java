package com.hao.time.controller;

import com.hao.common.controller.BaseSpringController;
import com.hao.common.entity.coin.TimeCoin;
import com.hao.common.pojo.ResponseData;
import com.hao.time.service.TimeCoinService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.*;


@RestController
public class TimeCoinController extends BaseSpringController {
    @Autowired
    private TimeCoinService timeCoinService;

    @RequestMapping("/coin/addOrUpdate")
    public ResponseData<TimeCoin> addOrUpdateCoin(TimeCoin coin){
        ResponseData<TimeCoin> res = new ResponseData<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today  = sdf.format(new Date());
        coin.setMarkDay(today);
        coin.setUserId(this.getUserId());
        timeCoinService.addOrUpdateCoin(coin);
        res.setMessage("操作成功");
        res.setCode(ResponseData.SUCCESS_CODE);
        return res;
    }

    @RequestMapping("/coin/getTodayCoinData")
    public ResponseData<List<Map<String,Object>>> getTodayCoinData(String day){
        ResponseData<List<Map<String,Object>>> res = new ResponseData<>();
        Example example= new Example(TimeCoin.class);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today  = sdf.format(new Date());
        if(StringUtils.isNotBlank(day)){
        	today=day;
        }
        example.createCriteria()
                .andEqualTo("markDay",today)
                .andEqualTo("userId",this.getUserId());
        example.setOrderByClause("create_time asc");
        List<TimeCoin> data = timeCoinService.selectByExample(example);
        List<Map<String,Object>> itemData= new ArrayList<>();
        for (TimeCoin time:data){
            Map<String,Object> item = new HashMap<>();
            item.put("id",time.getId());
            item.put("type",time.getType());
            item.put("content",time.getRemark());
            itemData.add(item);
        }
        res.setData(itemData);
        res.setMessage("操作成功");
        res.setCode(ResponseData.SUCCESS_CODE);
        return res;
    }


}
