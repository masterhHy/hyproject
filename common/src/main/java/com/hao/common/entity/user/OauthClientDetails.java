package com.hao.common.entity.user;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OauthClientDetails implements Serializable {
    @Id
    private String clientId;

    private Integer accessTokenValidity;

    private String additionalInformation;

    private String authorities;

    private String authorizedGrantTypes;

    private String autoapprove;

    private String clientSecret;

    private Integer refreshTokenValidity;

    private String resourceIds;

    private String scope;

    private String webServerRedirectUri;

    private List<String> authorizedGrantTypesArr =new ArrayList<>();


    public List<String> getAuthorizedGrantTypesArr() {
        return authorizedGrantTypesArr;
    }

    public void setAuthorizedGrantTypesArr(List<String> authorizedGrantTypesArr) {
        this.authorizedGrantTypesArr = authorizedGrantTypesArr;
    }

    /**
     * @return client_id
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * @param clientId
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * @return access_token_validity
     */
    public Integer getAccessTokenValidity() {
        return accessTokenValidity;
    }

    /**
     * @param accessTokenValidity
     */
    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    /**
     * @return additional_information
     */
    public String getAdditionalInformation() {
        return additionalInformation;
    }

    /**
     * @param additionalInformation
     */
    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    /**
     * @return authorities
     */
    public String getAuthorities() {
        return authorities;
    }

    /**
     * @param authorities
     */
    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    /**
     * @return authorized_grant_types
     */
    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    /**
     * @param authorizedGrantTypes
     */
    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    /**
     * @return autoapprove
     */
    public String getAutoapprove() {
        return autoapprove;
    }

    /**
     * @param autoapprove
     */
    public void setAutoapprove(String autoapprove) {
        this.autoapprove = autoapprove;
    }

    /**
     * @return client_secret
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * @param clientSecret
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    /**
     * @return refresh_token_validity
     */
    public Integer getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    /**
     * @param refreshTokenValidity
     */
    public void setRefreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    /**
     * @return resource_ids
     */
    public String getResourceIds() {
        return resourceIds;
    }

    /**
     * @param resourceIds
     */
    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    /**
     * @return scope
     */
    public String getScope() {
        return scope;
    }

    /**
     * @param scope
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * @return web_server_redirect_uri
     */
    public String getWebServerRedirectUri() {
        return webServerRedirectUri;
    }

    /**
     * @param webServerRedirectUri
     */
    public void setWebServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri;
    }

    @Override
    public String toString() {
        return "OauthClientDetails{" +
                "clientId='" + clientId + '\'' +
                ", accessTokenValidity=" + accessTokenValidity +
                ", additionalInformation='" + additionalInformation + '\'' +
                ", authorities='" + authorities + '\'' +
                ", authorizedGrantTypes='" + authorizedGrantTypes + '\'' +
                ", autoapprove='" + autoapprove + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", refreshTokenValidity=" + refreshTokenValidity +
                ", resourceIds='" + resourceIds + '\'' +
                ", scope='" + scope + '\'' +
                ", webServerRedirectUri='" + webServerRedirectUri + '\'' +
                '}';
    }
}