package com.hao.time.entity;

import java.util.Date;

public class TimeCoin {
    private String id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 金币记录天
     */
    private String markDay;

    /**
     * 金币类型 1:学习 2 工作 3 娱乐
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
     * 金币记录天
     */
    private String remark;

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
     * 获取金币记录天
     *
     * @return mark_day - 金币记录天
     */
    public String getMarkDay() {
        return markDay;
    }

    /**
     * 设置金币记录天
     *
     * @param markDay 金币记录天
     */
    public void setMarkDay(String markDay) {
        this.markDay = markDay;
    }

    /**
     * 获取金币类型 1:学习 2 工作 3 娱乐
     *
     * @return type - 金币类型 1:学习 2 工作 3 娱乐
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置金币类型 1:学习 2 工作 3 娱乐
     *
     * @param type 金币类型 1:学习 2 工作 3 娱乐
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

    /**
     * 获取金币记录天
     *
     * @return remark - 金币记录天
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置金币记录天
     *
     * @param remark 金币记录天
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}