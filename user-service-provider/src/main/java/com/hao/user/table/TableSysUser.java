package com.hao.user.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "sys_user")
public class TableSysUser  {
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

    @Column(columnDefinition="varchar(255) COMMENT '电子邮箱'")
    private String email;

    @Column(columnDefinition="varchar(255) COMMENT '用户名字'")
    private String firstName;

    @Column(columnDefinition="int(2) COMMENT '0 男 1女'")
    private Integer sex;

    @Column(columnDefinition="varchar(11) COMMENT '用户电话'")
    private String phone;

    @Column(columnDefinition="varchar(255) COMMENT '密码'")
    private String password;

    @Column(columnDefinition="varchar(255) COMMENT '账号'")
    private String username;

    @Column(columnDefinition="varchar(2) COMMENT 'Y启用 N禁用'")
    private String isEnable;

    @Column(columnDefinition="int(2) COMMENT '注册来源 1 手机注册 2 微信注册'")
    private Integer registerSource;


    @Column(columnDefinition="varchar(255) COMMENT '用户头像'")
    private String headImg;

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Integer getRegisterSource() {
        return registerSource;
    }

    public void setRegisterSource(Integer registerSource) {
        this.registerSource = registerSource;
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
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return first_name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * 获取0 男 1女
     *
     * @return sex - 0 男 1女
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置0 男 1女
     *
     * @param sex 0 男 1女
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取Y启用 N禁用
     *
     * @return is_enable - Y启用 N禁用
     */
    public String getIsEnable() {
        return isEnable;
    }

    /**
     * 设置Y启用 N禁用
     *
     * @param isEnable Y启用 N禁用
     */
    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }
}