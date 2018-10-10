package com.hao.finance.entity;

import javax.persistence.*;

@Table(name = "splider_stock_code")
public class SpliderStockCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 股票代码
     */
    private String code;

    /**
     * 股票名称
     */
    private String name;

    /**
     * 股票类型 	1:上海	2:深圳
     */
    private Integer type;

    /**
     * 字典表code值
     */
    @Column(name = "ditionary_code")
    private String ditionaryCode;

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
     * 获取股票代码
     *
     * @return code - 股票代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置股票代码
     *
     * @param code 股票代码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取股票名称
     *
     * @return name - 股票名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置股票名称
     *
     * @param name 股票名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取股票类型 	1:上海	2:深圳
     *
     * @return type - 股票类型 	1:上海	2:深圳
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置股票类型 	1:上海	2:深圳
     *
     * @param type 股票类型 	1:上海	2:深圳
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取字典表code值
     *
     * @return ditionary_code - 字典表code值
     */
    public String getDitionaryCode() {
        return ditionaryCode;
    }

    /**
     * 设置字典表code值
     *
     * @param ditionaryCode 字典表code值
     */
    public void setDitionaryCode(String ditionaryCode) {
        this.ditionaryCode = ditionaryCode;
    }
}