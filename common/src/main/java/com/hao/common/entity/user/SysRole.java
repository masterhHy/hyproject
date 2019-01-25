package com.hao.common.entity.user;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class SysRole implements Serializable {
    @Id
    private String id;


    /**
     * Y 启用 N禁用
     */
    private String isEnable;

    /**
     * 角色名字
     */
    private String name;

    /**
     * 角色编码
     */
    private String code;


    private Date updateTime;
    private Date createTime;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
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
     * 获取角色名字
     *
     * @return name - 角色名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置角色名字
     *
     * @param name 角色名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取角色编码
     *
     * @return code - 角色编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置角色编码
     *
     * @param code 角色编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id='" + id + '\'' +
                ", isEnable='" + isEnable + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }
}