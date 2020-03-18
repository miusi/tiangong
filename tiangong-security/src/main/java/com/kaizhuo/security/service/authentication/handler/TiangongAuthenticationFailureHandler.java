package com.kaizhuo.security.service.authentication.handler;

import cn.hutool.json.JSONUtil;
import com.kaizhuo.core.model.dto.R;
import com.kaizhuo.security.error.SecurityError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: tiangong
 * @package: com.kaizhuo.security.auth.common
 * @description: 自定义授权失败处理
 * @author: miaochen
 * @create: 2020-02-29 12:12
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Slf4j
@Component
public class TiangongAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        R r = R.error(SecurityError.LOGIN_ERROR);
        r.setMsg("用户名或密码错误");
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
