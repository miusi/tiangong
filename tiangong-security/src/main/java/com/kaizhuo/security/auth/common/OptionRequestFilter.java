package com.kaizhuo.security.auth.common;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: tiangong
 * @package: com.kaizhuo.security.auth.common
 * @description: spring security 跨域处理
 * @author: miaochen
 * @create: 2020-02-29 12:16
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public class OptionRequestFilter extends OncePerRequestFilter {

    private static final String OPTIONS = "OPTIONS";

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (httpServletRequest.getMethod().equals(OPTIONS)) {
            httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
            httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletResponse.getHeader("Access-Control-Request-Headers"));
            return;
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
