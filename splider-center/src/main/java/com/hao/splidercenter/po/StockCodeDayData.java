package com.hao.splidercenter.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity(name="splider_stock_code_day_data")
@Table(name="splider_stock_code_day_data",indexes={@Index(name="sscdd_ix_stockCodeId",columnList="stockCodeId")})
public class StockCodeDayData implements ParentPo{
	@Id
	private String id;
	@Column(columnDefinition="varchar(50) COMMENT '开盘日期' ")
	private String openDate;
	@Column(columnDefinition="double COMMENT '开盘价格' ")
	private Double openPrice;
	@Column(columnDefinition="double COMMENT '收盘价格' ")
	private Double closePrice;
	@Column(columnDefinition="double COMMENT '最高价格' ")
	private Double topPrice;
	@Column(columnDefinition="double COMMENT '最低价格' ")
	private Double lowPrice;
	@Column(columnDefinition="double COMMENT '涨跌幅' ")
	private Double finalPercent;
	@Column(columnDefinition="double COMMENT '成交量(手)' ")
	private Double num;
	@Column(columnDefinition="double COMMENT '成交金额(亿)' ")
	private Double amount;
	@Column(columnDefinition="double COMMENT '换手率' ")
	private Double changeHand;
	@Column(columnDefinition="varchar(50) COMMENT '股票id' ")
	private String stockCodeId;
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
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public Double getOpenPrice() {
		return openPrice;
	}
	public void setOpenPrice(Double openPrice) {
		this.openPrice = openPrice;
	}
	public Double getClosePrice() {
		return closePrice;
	}
	public void setClosePrice(Double closePrice) {
		this.closePrice = closePrice;
	}
	public Double getTopPrice() {
		return topPrice;
	}
	public void setTopPrice(Double topPrice) {
		this.topPrice = topPrice;
	}
	public Double getLowPrice() {
		return lowPrice;
	}
	public void setLowPrice(Double lowPrice) {
		this.lowPrice = lowPrice;
	}
	public Double getFinalPercent() {
		return finalPercent;
	}
	public void setFinalPercent(Double finalPercent) {
		this.finalPercent = finalPercent;
	}
	public Double getNum() {
		return num;
	}
	public void setNum(Double num) {
		this.num = num;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public Double getChangeHand() {
		return changeHand;
	}
	public void setChangeHand(Double changeHand) {
		this.changeHand = changeHand;
	}
	public String getStockCodeId() {
		return stockCodeId;
	}
	public void setStockCodeId(String stockCodeId) {
		this.stockCodeId = stockCodeId;
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
	public String toString() {
		return "StockCodeDayData [id=" + id + ", openDate=" + openDate + ", openPrice=" + openPrice + ", closePrice="
				+ closePrice + ", topPrice=" + topPrice + ", lowPrice=" + lowPrice + ", finalPercent=" + finalPercent
				+ ", num=" + num + ", amount=" + amount + ", changeHand=" + changeHand + ", stockCodeId=" + stockCodeId
				+ "]";
	}
	@Override
	public String toWritingString() {
		Date now = new Date();
		String str=
				id+","+
				amount+","+
				changeHand+","+
				closePrice+","+
				finalPercent+","+
				lowPrice+","+
				num+","+
				openDate+","+
				openPrice+","+
				stockCodeId+","+
				topPrice+","+
				now.toGMTString() + "," +
				zUpdateTime 
				;
		
		return str.replace("null", "\\N");	
	}
	
	
	
	
}
