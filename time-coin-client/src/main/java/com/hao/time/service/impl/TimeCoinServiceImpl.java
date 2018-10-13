package com.hao.time.service.impl;

import com.hao.common.utils.UUID;
import com.hao.time.dao.TimeCoinMapper;
import com.hao.time.entity.TimeCoin;
import com.hao.time.service.TimeCoinService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TimeCoinServiceImpl extends BaseServiceImpl<TimeCoin> implements TimeCoinService {
    @Autowired
    private TimeCoinMapper timeCoinMapper;
    @Override
    public void addOrUpdateCoin(TimeCoin coin) {
        if(StringUtils.isNotBlank(coin.getId())){
            //更新操作
            coin.setUpdateTime(new Date());
            timeCoinMapper.updateByPrimaryKeySelective(coin);
        }else {
            //添加操作
            coin.setId(UUID.uuid32());
            coin.setCreateTime(new Date());
            timeCoinMapper.insertSelective(coin);
        }
    }
}
