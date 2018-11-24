package com.hao.finance.entity.po;

import com.hao.common.pojo.BaseRequestPojo;

public class StockCodeQuery extends BaseRequestPojo {
    private Integer investorType;
    private String investorName;
    private String comment;
    private Double investorBuy;

    private Double codeProfit;
    private String codeIndustry;
    private Integer profitIncreaseType;// 1 连续增长 2 扭亏为盈 3 利润暴增 4 加速增长

    public Integer getInvestorType() {
        return investorType;
    }

    public void setInvestorType(Integer investorType) {
        this.investorType = investorType;
    }

    public String getInvestorName() {
        return investorName;
    }

    public void setInvestorName(String investorName) {
        this.investorName = "%"+investorName+"%";
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = "%"+comment+"%";
    }

    public Double getInvestorBuy() {
        return investorBuy;
    }

    public void setInvestorBuy(Double investorBuy) {
        this.investorBuy = investorBuy;
    }

    public Double getCodeProfit() {
        return codeProfit;
    }

    public void setCodeProfit(Double codeProfit) {
        this.codeProfit = codeProfit;
    }

    public String getCodeIndustry() {
        return codeIndustry;
    }

    public void setCodeIndustry(String codeIndustry) {
        this.codeIndustry = codeIndustry;
    }

    public Integer getProfitIncreaseType() {
        return profitIncreaseType;
    }

    public void setProfitIncreaseType(Integer profitIncreaseType) {
        this.profitIncreaseType = profitIncreaseType;
    }
}
