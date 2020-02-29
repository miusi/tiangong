package com.kaizhuo.security.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kaizhuo.core.security.TiangongSecurityUtil;
import com.kaizhuo.security.config.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: tiangong
 * @package: com.kaizhuo.security.auth.jwt
 * @description: jwt授权验证
 * @author: miaochen
 * @create: 2020-02-29 13:42
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

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
                        TiangongSecurityUtil.setLoginInfo(username, null);
                        JwtAuthenticationToken authentication = new JwtAuthenticationToken(userDetails, decodedJWT, null);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            } catch (Exception e) {
                logger.error("wrong token attempted:", e);
            }

        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
