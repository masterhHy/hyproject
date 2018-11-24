package com.hao.time.service;

import com.hao.time.entity.TimeCoin;

public interface TimeCoinService extends BaseService<TimeCoin> {

    public void addOrUpdateCoin(TimeCoin coin);
}
