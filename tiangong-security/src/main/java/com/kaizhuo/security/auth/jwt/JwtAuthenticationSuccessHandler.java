package com.kaizhuo.security.auth.jwt;

import cn.hutool.json.JSONUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kaizhuo.core.model.dto.R;
import com.kaizhuo.security.config.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: tiangong
 * @package: com.kaizhuo.security.auth.jwt
 * @description: 自定义登录成功
 * @author: miaochen
 * @create: 2020-02-29 13:39
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Slf4j
@Component
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        DecodedJWT jwt = ((JwtAuthenticationToken) authentication).getToken();
        String token = jwt.getToken();
        httpServletResponse.setHeader(SecurityProperties.authKey, token);
        PrintWriter writer = null;
        try {
            Map<String, String> result = new HashMap<>();
            result.put("token", token);
            R r = R.ok(result);
            writer = httpServletResponse.getWriter();
            writer.write(JSONUtil.toJsonStr(r));
        } catch (Exception ex) {
            log.error("deal login success error");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
