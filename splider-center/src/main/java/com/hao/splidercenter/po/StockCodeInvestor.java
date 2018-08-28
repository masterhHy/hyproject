package com.hao.splidercenter.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity(name="splider_stock_code_investor")
@Table(name="splider_stock_code_investor",indexes={@Index(name="publicDate_ix",columnList="publicDate,spliderStockCodeId")})
public class StockCodeInvestor implements ParentPo {
	@Id
	private String id;
	@Column(columnDefinition="varchar(50) COMMENT '股票id' ")
	private String spliderStockCodeId;
	@Column(columnDefinition="varchar(50) COMMENT '投资公司id' ")
	private String spliderInvestorId;
	@Column(columnDefinition="double COMMENT '持股数量(万股)' ")
	private Double codeQuantity;
	@Column(columnDefinition="double COMMENT '占流通股比例' ")
	private Double codePercent;
	@Column(columnDefinition="varchar(50) COMMENT '季度' ")
	private String publicDate;
	
	@Column(columnDefinition="varchar(50) COMMENT '增减情况' ")
	private String comment;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSpliderStockCodeId() {
		return spliderStockCodeId;
	}

	public void setSpliderStockCodeId(String spliderStockCodeId) {
		this.spliderStockCodeId = spliderStockCodeId;
	}

	public String getSpliderInvestorId() {
		return spliderInvestorId;
	}

	public void setSpliderInvestorId(String spliderInvestorId) {
		this.spliderInvestorId = spliderInvestorId;
	}

	public Double getCodeQuantity() {
		return codeQuantity;
	}

	public void setCodeQuantity(Double codeQuantity) {
		this.codeQuantity = codeQuantity;
	}

	public Double getCodePercent() {
		return codePercent;
	}

	public void setCodePercent(Double codePercent) {
		this.codePercent = codePercent;
	}

	public String getPublicDate() {
		return publicDate;
	}

	public void setPublicDate(String publicDate) {
		this.publicDate = publicDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "StockCodeInvestor [id=" + id + ", spliderStockCodeId=" + spliderStockCodeId + ", spliderInvestorId="
				+ spliderInvestorId + ", codeQuantity=" + codeQuantity + ", codePercent=" + codePercent
				+ ", publicDate=" + publicDate + ", comment=" + comment + "]";
	}

	@Override
	public String toWritingString() {
		String str=id +"," + codePercent+ "," + codeQuantity + "," 
					+ comment + "," + publicDate + ","
							+ spliderInvestorId + "," + spliderStockCodeId   ;
		str = str.replace("null", "");
		return str;
	}
	
	
}
