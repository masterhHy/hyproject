package com.hao.finance.entity;

import javax.persistence.*;

@Table(name = "splider_dictionary")
public class SpliderDictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 用来关联code值
     */
    private String code;

    /**
     * 名字
     */
    private String name;

    /**
     * 父类id 若为null则为根
     */
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 分类表示
     */
    private String sort;

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
     * 获取用来关联code值
     *
     * @return code - 用来关联code值
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置用来关联code值
     *
     * @param code 用来关联code值
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取名字
     *
     * @return name - 名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名字
     *
     * @param name 名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取父类id 若为null则为根
     *
     * @return parent_id - 父类id 若为null则为根
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置父类id 若为null则为根
     *
     * @param parentId 父类id 若为null则为根
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取分类表示
     *
     * @return sort - 分类表示
     */
    public String getSort() {
        return sort;
    }

    /**
     * 设置分类表示
     *
     * @param sort 分类表示
     */
    public void setSort(String sort) {
        this.sort = sort;
    }
}