package com.hao.splidercenter.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hao.splidercenter.po.StockCodeInvestor;

public interface StockCodeInvestorDao extends JpaRepository<StockCodeInvestor,String> {

	
	public List<StockCodeInvestor> findByPublicDateAndSpliderStockCodeId(String publicDate,String spliderStockCodeId);
}
