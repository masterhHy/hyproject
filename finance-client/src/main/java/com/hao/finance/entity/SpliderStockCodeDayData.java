package com.hao.finance.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "splider_stock_code_day_data")
public class SpliderStockCodeDayData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 成交金额(亿)
     */
    private Double amount;

    /**
     * 换手率
     */
    @Column(name = "change_hand")
    private Double changeHand;

    /**
     * 收盘价格
     */
    @Column(name = "close_price")
    private Double closePrice;

    /**
     * 涨跌幅
     */
    @Column(name = "final_percent")
    private Double finalPercent;

    /**
     * 最低价格
     */
    @Column(name = "low_price")
    private Double lowPrice;

    /**
     * 成交量(手)
     */
    private Double num;

    /**
     * 开盘日期
     */
    @Column(name = "open_date")
    private String openDate;

    /**
     * 开盘价格
     */
    @Column(name = "open_price")
    private Double openPrice;

    /**
     * 股票id
     */
    @Column(name = "stock_code_id")
    private String stockCodeId;

    /**
     * 最高价格
     */
    @Column(name = "top_price")
    private Double topPrice;

    /**
     * 创建时间
     */
    @Column(name = "z_creat_time")
    private Date zCreatTime;

    /**
     * 修改时间
     */
    @Column(name = "z_update_time")
    private Date zUpdateTime;

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
     * 获取成交金额(亿)
     *
     * @return amount - 成交金额(亿)
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * 设置成交金额(亿)
     *
     * @param amount 成交金额(亿)
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * 获取换手率
     *
     * @return change_hand - 换手率
     */
    public Double getChangeHand() {
        return changeHand;
    }

    /**
     * 设置换手率
     *
     * @param changeHand 换手率
     */
    public void setChangeHand(Double changeHand) {
        this.changeHand = changeHand;
    }

    /**
     * 获取收盘价格
     *
     * @return close_price - 收盘价格
     */
    public Double getClosePrice() {
        return closePrice;
    }

    /**
     * 设置收盘价格
     *
     * @param closePrice 收盘价格
     */
    public void setClosePrice(Double closePrice) {
        this.closePrice = closePrice;
    }

    /**
     * 获取涨跌幅
     *
     * @return final_percent - 涨跌幅
     */
    public Double getFinalPercent() {
        return finalPercent;
    }

    /**
     * 设置涨跌幅
     *
     * @param finalPercent 涨跌幅
     */
    public void setFinalPercent(Double finalPercent) {
        this.finalPercent = finalPercent;
    }

    /**
     * 获取最低价格
     *
     * @return low_price - 最低价格
     */
    public Double getLowPrice() {
        return lowPrice;
    }

    /**
     * 设置最低价格
     *
     * @param lowPrice 最低价格
     */
    public void setLowPrice(Double lowPrice) {
        this.lowPrice = lowPrice;
    }

    /**
     * 获取成交量(手)
     *
     * @return num - 成交量(手)
     */
    public Double getNum() {
        return num;
    }

    /**
     * 设置成交量(手)
     *
     * @param num 成交量(手)
     */
    public void setNum(Double num) {
        this.num = num;
    }

    /**
     * 获取开盘日期
     *
     * @return open_date - 开盘日期
     */
    public String getOpenDate() {
        return openDate;
    }

    /**
     * 设置开盘日期
     *
     * @param openDate 开盘日期
     */
    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    /**
     * 获取开盘价格
     *
     * @return open_price - 开盘价格
     */
    public Double getOpenPrice() {
        return openPrice;
    }

    /**
     * 设置开盘价格
     *
     * @param openPrice 开盘价格
     */
    public void setOpenPrice(Double openPrice) {
        this.openPrice = openPrice;
    }

    /**
     * 获取股票id
     *
     * @return stock_code_id - 股票id
     */
    public String getStockCodeId() {
        return stockCodeId;
    }

    /**
     * 设置股票id
     *
     * @param stockCodeId 股票id
     */
    public void setStockCodeId(String stockCodeId) {
        this.stockCodeId = stockCodeId;
    }

    /**
     * 获取最高价格
     *
     * @return top_price - 最高价格
     */
    public Double getTopPrice() {
        return topPrice;
    }

    /**
     * 设置最高价格
     *
     * @param topPrice 最高价格
     */
    public void setTopPrice(Double topPrice) {
        this.topPrice = topPrice;
    }

    /**
     * 获取创建时间
     *
     * @return z_creat_time - 创建时间
     */
    public Date getzCreatTime() {
        return zCreatTime;
    }

    /**
     * 设置创建时间
     *
     * @param zCreatTime 创建时间
     */
    public void setzCreatTime(Date zCreatTime) {
        this.zCreatTime = zCreatTime;
    }

    /**
     * 获取修改时间
     *
     * @return z_update_time - 修改时间
     */
    public Date getzUpdateTime() {
        return zUpdateTime;
    }

    /**
     * 设置修改时间
     *
     * @param zUpdateTime 修改时间
     */
    public void setzUpdateTime(Date zUpdateTime) {
        this.zUpdateTime = zUpdateTime;
    }
}