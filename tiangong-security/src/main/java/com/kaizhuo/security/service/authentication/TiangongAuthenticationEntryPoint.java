package com.kaizhuo.security.service.authentication;

import cn.hutool.json.JSONUtil;
import com.kaizhuo.core.model.dto.R;
import com.kaizhuo.security.error.SecurityError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: tiangong
 * @package: com.kaizhuo.security.auth.common
 * @description: 自定义未登录
 * @author: miaochen
 * @create: 2020-02-29 12:09
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Component
@Slf4j
public class TiangongAuthenticationEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        R r = R.error(SecurityError.USER_NOT_LOGIN);
        PrintWriter writer = null;
        try {
            writer = httpServletResponse.getWriter();
            writer.write(JSONUtil.toJsonStr(r));
        } catch (Exception ex) {
            log.error("deal login failure error");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
