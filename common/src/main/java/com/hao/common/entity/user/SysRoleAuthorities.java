package com.hao.common.entity.user;

import java.io.Serializable;

public class SysRoleAuthorities implements Serializable {
    private String id;
    private String sysRoleId;

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