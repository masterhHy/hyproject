package com.hao.common.entity.user;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysAuthority implements Serializable {
    @Id
    private String id;

    private String createdBy;

    private Date createdDate;

    private String lastModifiedBy;

    private Date lastModifiedDate;

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
     * @return created_by
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return created_date
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return last_modified_by
     */
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * @param lastModifiedBy
     */
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * @return last_modified_date
     */
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * @param lastModifiedDate
     */
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
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
}