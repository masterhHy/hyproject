package com.hao.finance.entity;

import javax.persistence.*;

@Table(name = "splider_stock_code_detail")
public class SpliderStockCodeDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 股票平均成本（上季度到这季度 平均买入成本）
     */
    private Double price;

    /**
     * 利润（单位：亿）
     */
    private Double profit;

    /**
     * 利润环比 （一季度利润与上年一季度利润做对比）
     */
    @Column(name = "profit_percent")
    private Double profitPercent;

    /**
     * 季度日期
     */
    @Column(name = "pub_date")
    private String pubDate;

    /**
     * 股票代码
     */
    @Column(name = "stock_code_id")
    private String stockCodeId;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取股票平均成本（上季度到这季度 平均买入成本）
     *
     * @return price - 股票平均成本（上季度到这季度 平均买入成本）
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置股票平均成本（上季度到这季度 平均买入成本）
     *
     * @param price 股票平均成本（上季度到这季度 平均买入成本）
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获取利润（单位：亿）
     *
     * @return profit - 利润（单位：亿）
     */
    public Double getProfit() {
        return profit;
    }

    /**
     * 设置利润（单位：亿）
     *
     * @param profit 利润（单位：亿）
     */
    public void setProfit(Double profit) {
        this.profit = profit;
    }

    /**
     * 获取利润环比 （一季度利润与上年一季度利润做对比）
     *
     * @return profit_percent - 利润环比 （一季度利润与上年一季度利润做对比）
     */
    public Double getProfitPercent() {
        return profitPercent;
    }

    /**
     * 设置利润环比 （一季度利润与上年一季度利润做对比）
     *
     * @param profitPercent 利润环比 （一季度利润与上年一季度利润做对比）
     */
    public void setProfitPercent(Double profitPercent) {
        this.profitPercent = profitPercent;
    }

    /**
     * 获取季度日期
     *
     * @return pub_date - 季度日期
     */
    public String getPubDate() {
        return pubDate;
    }

    /**
     * 设置季度日期
     *
     * @param pubDate 季度日期
     */
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    /**
     * 获取股票代码
     *
     * @return stock_code_id - 股票代码
     */
    public String getStockCodeId() {
        return stockCodeId;
    }

    /**
     * 设置股票代码
     *
     * @param stockCodeId 股票代码
     */
    public void setStockCodeId(String stockCodeId) {
        this.stockCodeId = stockCodeId;
    }
}