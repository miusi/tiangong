package com.kaizhuo.security.auth.jwt;

import cn.hutool.crypto.SecureUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kaizhuo.security.auth.common.TiangongUserDetails;
import com.kaizhuo.security.config.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @program: tiangong
 * @package: com.kaizhuo.security.auth.jwt
 * @description: 自定义登录认证
 * @author: miaochen
 * @create: 2020-02-29 12:29
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Slf4j
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        DecodedJWT decodedJWT = null;
        if (authentication instanceof JwtAuthenticationToken) {
            JwtAuthenticationToken jwtToken = (JwtAuthenticationToken) authentication;
            decodedJWT = jwtToken.getToken();

            // token 验证过期
            boolean isExpired = isExpired(decodedJWT.getExpiresAt());
            if (isExpired) {
                throw new AccountExpiredException("token已过期");
            }
        } else {
            //验证密码
            String encodePwd = SecureUtil.sha256(SecureUtil.sha256(username) + SecureUtil.sha256(password));
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
            if (!userDetails.getPassword().equals(encodePwd)) {
                throw new BadCredentialsException("用户名密码不正确，请重新登陆！");
            }
            String token = jwtUserDetailsService.loginSuccess(userDetails);
            decodedJWT = JWT.decode(token);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        TiangongUserDetails tiangongUserDetails = new TiangongUserDetails(username, password);
        return new JwtAuthenticationToken(tiangongUserDetails, decodedJWT, null);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    /**
     * 判断token是否过期
     *
     * @param issueAt token过期时间
     * @return 是否过期
     */
    private boolean isExpired(Date issueAt) {
        LocalDateTime issueTime = LocalDateTime.ofInstant(issueAt.toInstant(), ZoneId.systemDefault());
        return LocalDateTime.now().minusSeconds(securityProperties.getTokenExpiredTime()).isAfter(issueTime);
    }
}
