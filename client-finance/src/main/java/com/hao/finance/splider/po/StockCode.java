package com.hao.finance.splider.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity(name="splider_stock_code")
public class StockCode implements ParentPo {
	@Id
	private String id;
	@Column(columnDefinition="varchar(50) COMMENT '股票代码' ")
	private String code;
	@Column(columnDefinition="varchar(50) COMMENT '股票名称' ")
	private String name;
	@Column(columnDefinition="int(3) COMMENT '股票类型 	1:上海	2:深圳' ")
	private Integer type;
	/**
	 * 上海
	 */
	public static final Integer TYPE_SH=1;
	/**
	 * 深圳
	 */
	public static final Integer TYPE_SZ=2;
	
	@Column(columnDefinition="varchar(50) COMMENT '字典表code值' ")
	private String ditionaryCode;
	
	@Column(columnDefinition="datetime COMMENT '创建时间' ")
	private Date zCreatTime;
	@Column(columnDefinition="datetime COMMENT '修改时间' ")
	private Date zUpdateTime;
	
	
	public String getDitionaryCode() {
		return ditionaryCode;
	}
	public void setDitionaryCode(String ditionaryCode) {
		this.ditionaryCode = ditionaryCode;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "StockCode [id=" + id + ", code=" + code + ", name=" + name + ", type=" + type + "]";
	}
	@Override
	public String toWritingString() {
		// TODO Auto-generated method stub
		return null;
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
	
	
	
}
