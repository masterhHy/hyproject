package com.hao.splidercenter.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hao.splidercenter.po.StockCodeDayData;

public interface StockCodeDayDataDao extends JpaRepository<StockCodeDayData,String> {
	
	public List<StockCodeDayData> findByStockCodeIdAndOpenDateBetween(String stockCodeId,String startDay,String endDay);
}
