package com.hao.user.table;

import com.hao.common.pojo.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "sys_user")
public class TableSysUser  extends BaseEntity {
    @Id
    private String id;


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