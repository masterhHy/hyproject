package com.hao.splidercenter.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hao.splidercenter.po.StockCodeDetail;

public interface StockCodeDetailDao extends JpaRepository<StockCodeDetail,String> {

	public List<StockCodeDetail> findByPubDateAndPriceIsNull(String pubDate);
	public StockCodeDetail findByPubDateAndStockCodeId(String pubDate,String stockCodeId);
	
	@Query(value="SELECT GROUP_CONCAT(id) ids FROM splider_stock_code_detail d GROUP BY d.pub_date,d.stock_code_id HAVING COUNT(*)>1 ",nativeQuery=true)
	public List<Map<String,Object>> getDoubleDataIds();
	
	

}
