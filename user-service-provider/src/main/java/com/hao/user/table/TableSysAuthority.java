package com.hao.user.table;

import com.hao.common.pojo.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "sys_authority")
public class TableSysAuthority extends BaseEntity {
    @Id
    private String id;


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

    @Column(columnDefinition="varchar(255) COMMENT '权限名字的i18n标识'")
    private String signCode;

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