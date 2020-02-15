package com.kaizhuo.auth.service.authenication;

import com.kaizhuo.auth.service.interfaces.UsernamePasswordUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: tiangong
 * @package: com.kaizhuo.auth.service.authenication
 * @description: 用于实现用户名和密码认证
 * @author: miaochen
 * @create: 2020-02-14 19:11
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private UsernamePasswordUserDetailService usernamePasswordUserDetailService;

    public UsernamePasswordAuthenticationProvider(UsernamePasswordUserDetailService usernamePasswordUserDetailService) {
        this.usernamePasswordUserDetailService = usernamePasswordUserDetailService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName=authentication.getName();
        String password=authentication.getCredentials().toString();
        if(usernamePasswordUserDetailService.verifyCredential(userName,password)){
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("user"));
            UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(userName,password,authorities);
            token.setDetails(usernamePasswordUserDetailService.getUserDetails(userName));
            return token;
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        // 用户名和密码登录时，使用该provider认证
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass));
    }
}
