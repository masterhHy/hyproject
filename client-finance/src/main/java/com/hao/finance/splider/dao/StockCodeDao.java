package com.hao.finance.splider.dao;

import com.hao.finance.splider.po.StockCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockCodeDao extends JpaRepository<StockCode,String> {
	
	/**
	 * 获取没有行业的股票代码
	 * @return
	 */
	public List<StockCode> findByDitionaryCodeIsNull();
	
	public List<StockCode> findByCode(String code);
	
}
