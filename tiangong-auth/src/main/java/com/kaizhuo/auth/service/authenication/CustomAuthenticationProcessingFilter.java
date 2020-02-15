package com.kaizhuo.auth.service.authenication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @program: tiangong
 * @package: com.kaizhuo.auth.service.authenication
 * @description: 自定义授权处理过滤器
 * @author: miaochen
 * @create: 2020-02-14 19:43
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public class CustomAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private static final String AUTH_TYPE = "auth_type";
    private static final String USERNAME = "username";
    private static final String CREDENTIALS = "credentials";
    private static final String OAUTH_TOKEN_URL = "/oauth/custom/token";
    private static final String HTTP_METHOD_POST = "POST";

    public CustomAuthenticationProcessingFilter() {
        super(new AntPathRequestMatcher(OAUTH_TOKEN_URL, HTTP_METHOD_POST));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        //请求方式过滤
        if (!HTTP_METHOD_POST.equals(httpServletRequest.getMethod().toUpperCase())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + httpServletRequest.getMethod());
        }
        AbstractAuthenticationToken authRequest = new CustomAuthenticationToken(
                httpServletRequest.getParameter(AUTH_TYPE), httpServletRequest.getParameter(USERNAME), httpServletRequest.getParameter(CREDENTIALS),
                httpServletRequest.getParameterMap(),new ArrayList<>()
        );

        this.setDetails(httpServletRequest,authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected void setDetails(HttpServletRequest request, AbstractAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }
}
