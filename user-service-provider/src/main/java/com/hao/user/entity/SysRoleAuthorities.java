package com.hao.user.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "sys_role_authorities")
public class SysRoleAuthorities implements Serializable {
    @Id
    private String id;
    @Column(name = "sys_role_id")
    private String sysRoleId;

    @Column(name = "authorities_id")
    private String authoritiesId;

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