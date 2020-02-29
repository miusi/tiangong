package com.kaizhuo.security.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kaizhuo.security.config.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @program: tiangong
 * @package: com.kaizhuo.security.auth.jwt
 * @description: token 刷新 filter
 * @author: miaochen
 * @create: 2020-02-29 13:29
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Component
public class JwtAuthenticationRefreshFilter extends OncePerRequestFilter {


    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String reqUri = httpServletRequest.getRequestURI();
        String authHeader = httpServletRequest.getHeader(SecurityProperties.authKey);
        if (authHeader != null && authHeader.startsWith(SecurityProperties.authBearKey) && !reqUri.equals(securityProperties.getLoginUrl())) {
            final String authToken = authHeader.substring(SecurityProperties.authBearKey.length());
            try {
                DecodedJWT decodedJWT = JWT.decode(authToken);
                String username = decodedJWT.getSubject();
                if (username != null) {
                    UserDetails userDetails = jwtUserDetailsService.getUserLoginInfo(username);
                    if (userDetails != null) {
                        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                        httpServletResponse.setCharacterEncoding("UTF-8");
                        httpServletResponse.setContentType("application/json; charset=utf-8");
                        String token = decodedJWT.getToken();
                        boolean shouldRefresh = shouldTokenRefresh(decodedJWT.getIssuedAt());
                        if (shouldRefresh) {
                            token = jwtUserDetailsService.loginSuccess(userDetails);
                        }
                        httpServletResponse.setHeader(SecurityProperties.authKey, token);
                    }
                }
            } catch (Exception e) {
                logger.error("wrong token attempted:", e);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    protected boolean shouldTokenRefresh(Date issueAt) {
        LocalDateTime issueTime = LocalDateTime.ofInstant(issueAt.toInstant(), ZoneId.systemDefault());
        int tokenRefreshSec = securityProperties.getTokenExpiredTime();
        return LocalDateTime.now().minusSeconds(tokenRefreshSec).isAfter(issueTime);
    }
}
