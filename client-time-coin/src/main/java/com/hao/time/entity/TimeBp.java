package com.hao.time.entity;

import java.util.Date;

public class TimeBp {
    private String id;

    /**
     * 积分总数
     */
    private Integer amount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 积分类型
     */
    private Integer type;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 用户id
     */
    private String userId;

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
     * 获取积分总数
     *
     * @return amount - 积分总数
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * 设置积分总数
     *
     * @param amount 积分总数
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取积分类型
     *
     * @return type - 积分类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置积分类型
     *
     * @param type 积分类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
}