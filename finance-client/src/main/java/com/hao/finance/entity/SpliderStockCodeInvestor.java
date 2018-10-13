package com.hao.finance.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "splider_stock_code_investor")
public class SpliderStockCodeInvestor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 占流通股比例
     */
    @Column(name = "code_percent")
    private Double codePercent;

    /**
     * 持股数量(万股)
     */
    @Column(name = "code_quantity")
    private Double codeQuantity;

    /**
     * 增减情况
     */
    private String comment;

    /**
     * 季度
     */
    @Column(name = "public_date")
    private String publicDate;

    /**
     * 投资公司id
     */
    @Column(name = "splider_investor_id")
    private String spliderInvestorId;

    /**
     * 股票id
     */
    @Column(name = "splider_stock_code_id")
    private String spliderStockCodeId;

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
     * 获取占流通股比例
     *
     * @return code_percent - 占流通股比例
     */
    public Double getCodePercent() {
        return codePercent;
    }

    /**
     * 设置占流通股比例
     *
     * @param codePercent 占流通股比例
     */
    public void setCodePercent(Double codePercent) {
        this.codePercent = codePercent;
    }

    /**
     * 获取持股数量(万股)
     *
     * @return code_quantity - 持股数量(万股)
     */
    public Double getCodeQuantity() {
        return codeQuantity;
    }

    /**
     * 设置持股数量(万股)
     *
     * @param codeQuantity 持股数量(万股)
     */
    public void setCodeQuantity(Double codeQuantity) {
        this.codeQuantity = codeQuantity;
    }

    /**
     * 获取增减情况
     *
     * @return comment - 增减情况
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置增减情况
     *
     * @param comment 增减情况
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 获取季度
     *
     * @return public_date - 季度
     */
    public String getPublicDate() {
        return publicDate;
    }

    /**
     * 设置季度
     *
     * @param publicDate 季度
     */
    public void setPublicDate(String publicDate) {
        this.publicDate = publicDate;
    }

    /**
     * 获取投资公司id
     *
     * @return splider_investor_id - 投资公司id
     */
    public String getSpliderInvestorId() {
        return spliderInvestorId;
    }

    /**
     * 设置投资公司id
     *
     * @param spliderInvestorId 投资公司id
     */
    public void setSpliderInvestorId(String spliderInvestorId) {
        this.spliderInvestorId = spliderInvestorId;
    }

    /**
     * 获取股票id
     *
     * @return splider_stock_code_id - 股票id
     */
    public String getSpliderStockCodeId() {
        return spliderStockCodeId;
    }

    /**
     * 设置股票id
     *
     * @param spliderStockCodeId 股票id
     */
    public void setSpliderStockCodeId(String spliderStockCodeId) {
        this.spliderStockCodeId = spliderStockCodeId;
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