package com.kaizhuo.auth.service.authenication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @program: tiangong
 * @package: com.kaizhuo.auth.service.authenication
 * @description: 自定义Token
 * @author: miaochen
 * @create: 2020-02-14 19:34
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public class CustomAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private String authType;

    private Map<String,String[]> authParams;

    private Object principal;

    private Object credentials;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public Map<String, String[]> getAuthParams() {
        return authParams;
    }

    public void setAuthParams(Map<String, String[]> authParams) {
        this.authParams = authParams;
    }

    public void setPrincipal(Object principal) {
        this.principal = principal;
    }

    public void setCredentials(Object credentials) {
        this.credentials = credentials;
    }

    public CustomAuthenticationToken(
            String authType, Object principal, Object credentials,
            Map<String, String[]> authParams, List<GrantedAuthority> authorities) {
        super(authorities);
        this.authType = authType;
        this.principal = principal;
        this.credentials = credentials;
        this.authParams = authParams;
        super.setAuthenticated(true);
    }


    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    public String getAuthParams(String parameter){
        String[] values = this.authParams.get(parameter);
        if(values != null && values.length > 0){
            return values[0];
        }
        return null;
    }
}
