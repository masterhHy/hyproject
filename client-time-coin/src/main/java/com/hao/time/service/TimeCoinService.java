package com.hao.time.service;


import com.hao.common.entity.coin.TimeCoin;

public interface TimeCoinService extends BaseService<TimeCoin> {

    public void addOrUpdateCoin(TimeCoin coin);

}
