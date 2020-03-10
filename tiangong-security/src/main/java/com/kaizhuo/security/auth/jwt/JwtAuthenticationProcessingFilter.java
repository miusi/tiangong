package com.kaizhuo.security.auth.jwt;

import cn.hutool.crypto.SecureUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY;
import static org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;

/**
 * @program: tiangong
 * @package: com.kaizhuo.security.auth.jwt
 * @description:
 * @author: godric
 * @create: 2020/3/8 0008
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: godric
 **/
public class JwtAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
    private static final String AUTH_TYPE = "auth_type";
    private static final String USERNAME = "username";
    private static final String CREDENTIALS = "password";
    private static final String OAUTH_TOKEN_URL = "/oauth/custom/token";
    private static final String HTTP_METHOD_POST = "POST";

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    public JwtAuthenticationProcessingFilter() {
        super(new AntPathRequestMatcher(OAUTH_TOKEN_URL,HTTP_METHOD_POST));
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        if (!HTTP_METHOD_POST.equals(httpServletRequest.getMethod().toUpperCase())){
            throw  new AuthenticationServiceException("Authentication method not supported: " + httpServletRequest.getMethod());
        }

        String username=httpServletRequest.getParameter(SPRING_SECURITY_FORM_USERNAME_KEY);
        String password=httpServletRequest.getParameter(SPRING_SECURITY_FORM_PASSWORD_KEY);
        String encodePwd = SecureUtil.sha256(SecureUtil.sha256(username) + SecureUtil.sha256(password));
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
        if (!userDetails.getPassword().equals(encodePwd)) {
            throw new BadCredentialsException("用户名密码不正确，请重新登陆！");
        }
        String token = jwtUserDetailsService.loginSuccess(userDetails);
        DecodedJWT decodedJWT = JWT.decode(token);
        AbstractAuthenticationToken authenticationToken=new JwtAuthenticationToken(decodedJWT);
        this.setDetails(httpServletRequest, authenticationToken);
        return this.getAuthenticationManager().authenticate(authenticationToken);
    }

    protected void setDetails(HttpServletRequest request, AbstractAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }
}
