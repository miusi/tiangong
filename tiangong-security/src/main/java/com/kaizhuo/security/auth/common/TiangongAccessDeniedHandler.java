package com.kaizhuo.security.auth.common;

import cn.hutool.json.JSONUtil;
import com.kaizhuo.core.model.dto.R;
import com.kaizhuo.security.error.SecurityError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: tiangong
 * @package: com.kaizhuo.security.auth.common
 * @description: 自定义403响应内容
 * @author: miaochen
 * @create: 2020-02-29 12:07
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Slf4j
@Component
public class TiangongAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        R r = R.error(SecurityError.ACCESS_FORBIDDEN);
        PrintWriter writer = null;
        try {
            writer = httpServletResponse.getWriter();
            writer.write(JSONUtil.toJsonStr(r));
        } catch (Exception ex) {
            log.error("deal access denied error");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
