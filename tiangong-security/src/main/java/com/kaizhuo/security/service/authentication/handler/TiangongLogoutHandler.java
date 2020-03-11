package com.kaizhuo.security.service.authentication.handler;

import com.kaizhuo.security.service.impl.TiangongUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: tiangong
 * @package: com.kaizhuo.security.auth.jwt
 * @description: jwt登出处理
 * @author: miaochen
 * @create: 2020-02-29 13:46
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Component
public class TiangongLogoutHandler implements LogoutSuccessHandler {

    @Autowired
    private TiangongUserDetailsServiceImpl jwtUserDetailsService;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Authentication authentication) throws IOException, ServletException {
        if (authentication == null) {
            return;
        }

        UserDetails user = (UserDetails) authentication.getCredentials();

        if (user != null && user.getUsername() != null) {
            jwtUserDetailsService.deleteUserLoginInfo(user.getUsername());
        }
    }
}
