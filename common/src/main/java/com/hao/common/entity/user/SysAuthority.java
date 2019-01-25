package com.hao.common.entity.user;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysAuthority implements Serializable {
	@Id
    private String id;


    private String name;

    /**
     * 权限编码
     */
    private String code;

    /**
     * 父类id
     */
    private String parentId;

    /**
     * 图标
     */
    private String icon;

    /**
     * 拦截url
     */
    private String url;

    /**
     * 权重
     */
    private Integer orderNum;

    /**
     * 1 菜单;2 按钮
     */
    private Integer type;

    /**
     * Y 启用 N禁用
     */
    private String isEnable;

    private String projectName;
    private String signCode;
    private List<SysAuthority> children = new ArrayList<>();

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

    public List<SysAuthority> getChildren() {
        return children;
    }

    public void setChildren(List<SysAuthority> children) {
        this.children = children;
    }

    public String getSignCode() {
        return signCode;
    }

    public void setSignCode(String signCode) {
        this.signCode = signCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取权限编码
     *
     * @return code - 权限编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置权限编码
     *
     * @param code 权限编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取父类id
     *
     * @return parent_id - 父类id
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置父类id
     *
     * @param parentId 父类id
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取图标
     *
     * @return icon - 图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图标
     *
     * @param icon 图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取拦截url
     *
     * @return url - 拦截url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置拦截url
     *
     * @param url 拦截url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取权重
     *
     * @return order_num - 权重
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * 设置权重
     *
     * @param orderNum 权重
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取1 菜单;2 按钮
     *
     * @return type - 1 菜单;2 按钮
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置1 菜单;2 按钮
     *
     * @param type 1 菜单;2 按钮
     */
    public void setType(Integer type) {
        this.type = type;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    @Override
    public String toString() {
        return "SysAuthority{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", parentId='" + parentId + '\'' +
                ", icon='" + icon + '\'' +
                ", url='" + url + '\'' +
                ", orderNum=" + orderNum +
                ", type=" + type +
                ", isEnable='" + isEnable + '\'' +
                ", projectName='" + projectName + '\'' +
                ", signCode='" + signCode + '\'' +
                ", children=" + children +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }
}