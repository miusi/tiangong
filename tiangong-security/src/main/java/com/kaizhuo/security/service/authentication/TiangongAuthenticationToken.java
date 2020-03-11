package com.kaizhuo.security.service.authentication;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * @program: tiangong
 * @package: com.kaizhuo.security.auth.jwt
 * @description: Jwt Token
 * @author: miaochen
 * @create: 2020-02-29 12:24
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public class TiangongAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = -5014446855926448435L;

    private String principal;
    private String credentials;
    private DecodedJWT token;

    public TiangongAuthenticationToken(DecodedJWT token) {
        super(Collections.emptyList());
        this.token = token;
    }

    public TiangongAuthenticationToken(UserDetails userDetails, DecodedJWT token, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = userDetails.getUsername();
        this.credentials = userDetails.getPassword();
        this.token = token;
    }

    @Override
    public void setDetails(Object details) {
        super.setDetails(details);
        this.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    public DecodedJWT getToken() {
        return token;
    }

    public void setToken(DecodedJWT token) {
        this.token = token;
    }
}
