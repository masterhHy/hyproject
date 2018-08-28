package com.hao.splidercenter.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hao.splidercenter.po.StockCode;

public interface StockCodeDao extends JpaRepository<StockCode,String> {
	
	/**
	 * 获取没有行业的股票代码
	 * @return
	 */
	public List<StockCode> findByDitionaryCodeIsNull();
	
	public List<StockCode> findByCode(String code);
	
}
