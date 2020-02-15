package com.kaizhuo.auth.service.authenication;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: tiangong
 * @package: com.kaizhuo.auth.service.authenication
 * @description: 验证失败处理
 * @author: miaochen
 * @create: 2020-02-14 20:11
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            AuthenticationException e) throws IOException, ServletException {
        int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        httpServletResponse.setStatus(status);
        httpServletResponse.getWriter().write("authentication failure");
    }
}
