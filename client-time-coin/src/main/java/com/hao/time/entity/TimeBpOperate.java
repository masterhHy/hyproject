package com.hao.time.entity;

import java.util.Date;

public class TimeBpOperate {
    private String id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 积分数
     */
    private Integer num;

    /**
     * 积分表id
     */
    private String timeBpId;

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
     * 获取积分数
     *
     * @return num - 积分数
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置积分数
     *
     * @param num 积分数
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取积分表id
     *
     * @return time_bp_id - 积分表id
     */
    public String getTimeBpId() {
        return timeBpId;
    }

    /**
     * 设置积分表id
     *
     * @param timeBpId 积分表id
     */
    public void setTimeBpId(String timeBpId) {
        this.timeBpId = timeBpId;
    }
}