package com.hao.finance.splider.dao;

import com.hao.finance.splider.po.StockCodeInvestor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockCodeInvestorDao extends JpaRepository<StockCodeInvestor,String> {

	
	public List<StockCodeInvestor> findByPublicDateAndSpliderStockCodeId(String publicDate, String spliderStockCodeId);
}
