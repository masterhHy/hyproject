package com.hao.common.entity.user;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class SysRoleAuthorities implements Serializable {
    @Id
    private String id;
    private String sysRoleId;

    private String authoritiesId;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return sys_role_id
     */
    public String getSysRoleId() {
        return sysRoleId;
    }

    /**
     * @param sysRoleId
     */
    public void setSysRoleId(String sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    /**
     * @return authorities_id
     */
    public String getAuthoritiesId() {
        return authoritiesId;
    }

    /**
     * @param authoritiesId
     */
    public void setAuthoritiesId(String authoritiesId) {
        this.authoritiesId = authoritiesId;
    }
}