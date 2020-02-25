package com.kaizhuo.core.security;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: miaochen
 * \* @Date: 2020/2/25
 * \* @Time: 15:09
 * \* To change this template use File | Settings | File Templates.
 * \* @Description: 操作人员
 * \
 */
public class UsernameAuditorBean implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        String username = TiangongSecurityUtil.getUsername();
        username = username == null ? "System" : username;
        Optional<String> auditor = Optional.of(username);
        return auditor;
    }
}
