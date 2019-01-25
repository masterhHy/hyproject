package com.hao.common.entity.user;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class SysUser implements Serializable {
    @Id
    private String id;


    private String email;

    private String firstName;

    /**
     * 0 男 1女
     */
    private Integer sex;

    private String phone;

    private String password;

    private String username;

    private Integer registerSource;

    private String headImg;

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
     * Y启用 N禁用
     */
    private String isEnable;

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

    @Override
    public String toString() {
        return "SysUser{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", sex=" + sex +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", registerSource=" + registerSource +
                ", headImg='" + headImg + '\'' +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                ", isEnable='" + isEnable + '\'' +
                '}';
    }
}