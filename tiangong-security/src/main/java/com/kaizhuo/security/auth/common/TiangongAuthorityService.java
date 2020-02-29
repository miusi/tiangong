package com.kaizhuo.security.auth.common;

import com.kaizhuo.core.security.TiangongSecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: tiangong
 * @package: com.kaizhuo.security.auth.common
 * @description: 权限验证
 * @author: miaochen
 * @create: 2020-02-29 12:03
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Slf4j
@Component("tiangongAuthorityService")
public class TiangongAuthorityService {
    @Autowired
    private TiangongSecurityService tiangongSecurityService;

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        boolean hasPermission = false;
        String username = (String) authentication.getPrincipal();
        if (username != null) {
            // 公共资源
            Set<String> noAuthUrls = new HashSet<>();
            AntPathMatcher antPathMatcher = new AntPathMatcher();
            for (String url : noAuthUrls) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }
            // 登录授权资源
            if (!hasPermission) {
                hasPermission = tiangongSecurityService.isUrlPermissionByName(username, request.getRequestURI());
            }
        }
        return hasPermission;
    }
}
