package com.hao.common.entity.user;

import javax.persistence.Id;
import java.io.Serializable;

public class SysUserRoles implements Serializable {
    @Id
    private String id;
    private String sysUserId;

    private String rolesId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return sys_user_id
     */
    public String getSysUserId() {
        return sysUserId;
    }

    /**
     * @param sysUserId
     */
    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    /**
     * @return roles_id
     */
    public String getRolesId() {
        return rolesId;
    }

    /**
     * @param rolesId
     */
    public void setRolesId(String rolesId) {
        this.rolesId = rolesId;
    }
}