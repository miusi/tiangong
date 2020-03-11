package com.kaizhuo.security.service.authentication.handler;

import cn.hutool.json.JSONUtil;
import com.kaizhuo.core.model.dto.R;
import com.kaizhuo.core.security.TiangongSecurityContext;
import com.kaizhuo.core.security.TiangongSecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: tiangong
 * @package: com.kaizhuo.security.auth.common
 * @description: 自定义登出成功
 * @author: miaochen
 * @create: 2020-02-29 12:14
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Slf4j
@Component
public class TiangongLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        R r = R.ok();
        PrintWriter writer = null;
        try {
            TiangongSecurityUtil.logout();
            writer = httpServletResponse.getWriter();
            writer.write(JSONUtil.toJsonStr(r));
        } catch (Exception ex) {
            log.error("deal logout success error");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
