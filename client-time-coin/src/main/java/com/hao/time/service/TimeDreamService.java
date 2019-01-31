package com.hao.time.service;


import java.util.List;

import com.hao.common.entity.coin.TimeDream;
import com.hao.common.query.coin.TimeDreamQuery;

public interface TimeDreamService extends BaseService<TimeDream> {

	public List<TimeDream> getDreamData(TimeDreamQuery query);
	public void addOrUpdate(TimeDream dream);
}
