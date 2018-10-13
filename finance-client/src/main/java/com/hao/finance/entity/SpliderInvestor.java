package com.hao.finance.entity;

import javax.persistence.*;

@Table(name = "splider_investor")
public class SpliderInvestor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 投资公司
     */
    private String name;

    /**
     * 公司类型 1:基金	2:保险公司		3:一般法人		4:信托公司		5:社保基金		6:QFII 	7:券商	8:券商集合理财		9:企业年金
     */
    private Integer type;

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
     * 获取投资公司
     *
     * @return name - 投资公司
     */
    public String getName() {
        return name;
    }

    /**
     * 设置投资公司
     *
     * @param name 投资公司
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取公司类型 1:基金	2:保险公司		3:一般法人		4:信托公司		5:社保基金		6:QFII 	7:券商	8:券商集合理财		9:企业年金
     *
     * @return type - 公司类型 1:基金	2:保险公司		3:一般法人		4:信托公司		5:社保基金		6:QFII 	7:券商	8:券商集合理财		9:企业年金
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置公司类型 1:基金	2:保险公司		3:一般法人		4:信托公司		5:社保基金		6:QFII 	7:券商	8:券商集合理财		9:企业年金
     *
     * @param type 公司类型 1:基金	2:保险公司		3:一般法人		4:信托公司		5:社保基金		6:QFII 	7:券商	8:券商集合理财		9:企业年金
     */
    public void setType(Integer type) {
        this.type = type;
    }
}