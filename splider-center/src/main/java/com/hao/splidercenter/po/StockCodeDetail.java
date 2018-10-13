package com.hao.splidercenter.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity(name="splider_stock_code_detail")
@Table(name="splider_stock_code_detail",indexes={@Index(name="scd_ix_publicDate",columnList="pubDate")})
public class StockCodeDetail implements ParentPo {
	@Id
	private String id;
	@Column(columnDefinition="varchar(50) COMMENT '股票代码' ")
	private String stockCodeId;
	@Column(columnDefinition="varchar(50) COMMENT '季度日期' ")
	private String pubDate;
	@Column(columnDefinition="double COMMENT '利润（单位：亿）' ")
	private Double profit;
	@Column(columnDefinition="double COMMENT '利润环比 （一季度利润与上年一季度利润做对比）' ")
	private Double profitPercent;
	@Column(columnDefinition="double COMMENT '股票平均成本（上季度到这季度 平均买入成本）' ")
	private Double price;
	
	@Column(columnDefinition="datetime COMMENT '创建时间' ")
	private Date zCreatTime;
	@Column(columnDefinition="datetime COMMENT '修改时间' ")
	private Date zUpdateTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStockCodeId() {
		return stockCodeId;
	}
	public void setStockCodeId(String stockCodeId) {
		this.stockCodeId = stockCodeId;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public Double getProfit() {
		return profit;
	}
	public void setProfit(Double profit) {
		this.profit = profit;
	}
	public Double getProfitPercent() {
		return profitPercent;
	}
	public void setProfitPercent(Double profitPercent) {
		this.profitPercent = profitPercent;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	
	public Date getzCreatTime() {
		return zCreatTime;
	}
	public void setzCreatTime(Date zCreatTime) {
		this.zCreatTime = zCreatTime;
	}
	public Date getzUpdateTime() {
		return zUpdateTime;
	}
	public void setzUpdateTime(Date zUpdateTime) {
		this.zUpdateTime = zUpdateTime;
	}
	@Override
	public String toWritingString() {
		Date now = new Date();
		String str=id + "," +
				   price  + "," + 
				   profit + "," + 
				   profitPercent + "," + 
				   pubDate + "," + 
				   stockCodeId + "," +
				   now.toGMTString() + "," +
				   zUpdateTime 
				  ;
		return str.replace("null", "\\N");
	}
	@Override
	public String toString() {
		return "StockCodeDetail [id=" + id + ", stockCodeId=" + stockCodeId + ", pubDate=" + pubDate + ", profit="
				+ profit + ", profitPercent=" + profitPercent + ", price=" + price + "]";
	}
	
	
	
	
	
	
}
