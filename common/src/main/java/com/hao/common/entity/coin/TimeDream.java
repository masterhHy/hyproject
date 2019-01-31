package com.hao.common.entity.coin;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class TimeDream implements Serializable {
	@Id
    private String id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 梦想基金钱 
     */
    private Double price;

    /**
     * 权重
     */
    private Integer priority;

    /**
     * 标题
     */
    private String title;

    /**
     * 用户表id
     */
    private String userId;

    /**
     * 描述
     */
    private String description;
    
    private Integer isDelete;
    
    
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

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
     * 获取梦想基金钱 
     *
     * @return price - 梦想基金钱 
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置梦想基金钱 
     *
     * @param price 梦想基金钱 
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获取权重
     *
     * @return priority - 权重
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * 设置权重
     *
     * @param priority 权重
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取用户表id
     *
     * @return user_id - 用户表id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户表id
     *
     * @param userId 用户表id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取描述
     *
     * @return description - 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }
}