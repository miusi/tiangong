package com.kaizhuo.auth.service.authenication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: tiangong
 * @package: com.kaizhuo.auth.service.authenication
 * @description: 自定义的登录认证
 * @author: miaochen
 * @create: 2020-02-14 19:31
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public class CustomAuthenticationProvider implements AuthenticationProvider {
    Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authentication.getPrincipal()==null){
            throw  new BadCredentialsException("用户名为空");
        }

        CustomAuthenticationToken customAuthenticationToken =(CustomAuthenticationToken) authentication;
        // 页面调用时传递auth_type参数，如手机验证码验证或者其它类型。根据不同的类型的auth type采用不同的验证方式验证是否登录成功
        if("mobile".equals(customAuthenticationToken.getAuthType())) {
            // 手机认证

        } else if("password".equals(customAuthenticationToken.getAuthType())) {
            // 用户名和密码

        } else {
            // 其它方式

        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("user"));

        CustomAuthenticationToken authenticationToken = new CustomAuthenticationToken
                (((CustomAuthenticationToken) authentication).getAuthType(), authentication.getPrincipal(), null,
                        null, authorities);

        Map<String, Object> details = new HashMap<>(1);
        details.put("name", customAuthenticationToken.getPrincipal());
        authenticationToken.setDetails(details);

        logger.info("[auth::login_success] " + customAuthenticationToken.getPrincipal() + " login success");

        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return (CustomAuthenticationToken.class.isAssignableFrom(aClass));
    }
}
