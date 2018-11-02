package com.hao.user.entity.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "sys_authority")
public class TableSysAuthority {
    @Id
    private String id;

    @Column(columnDefinition="varchar(255) COMMENT '创建人'")
    private String createdBy;

    @Column(columnDefinition="datetime COMMENT '创建时间'")
    private Date createdDate;

    @Column(columnDefinition="varchar(255) COMMENT '修改人'")
    private String lastModifiedBy;

    @Column(columnDefinition="datetime COMMENT '修改时间'")
    private Date lastModifiedDate;

    @Column(columnDefinition="varchar(255) COMMENT '权限名字'")
    private String name;



    @Column(columnDefinition="varchar(255) COMMENT '权限编码'")
    private String code;

    @Column(columnDefinition="varchar(255) COMMENT '父类id'")
    private String parentId;

    @Column(columnDefinition="varchar(255) COMMENT '图标'")
    private String icon;

    @Column(columnDefinition="varchar(255) COMMENT '拦截url'")
    private String url;

    @Column(columnDefinition="int(11) COMMENT '权重'")
    private Integer orderNum;

    @Column(columnDefinition="int(2) COMMENT '1 菜单;2 按钮'")
    private Integer type;

    @Column(columnDefinition="varchar(10) COMMENT 'Y 启用 N禁用'")
    private String isEnable;

    @Column(columnDefinition="varchar(255) COMMENT '项目名字'")
    private String projectName;

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