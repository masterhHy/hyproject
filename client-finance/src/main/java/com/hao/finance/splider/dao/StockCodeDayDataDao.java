package com.hao.finance.splider.dao;

import com.hao.finance.splider.po.StockCodeDayData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockCodeDayDataDao extends JpaRepository<StockCodeDayData,String> {
	
	public List<StockCodeDayData> findByStockCodeIdAndOpenDateBetween(String stockCodeId, String startDay, String endDay);
}
