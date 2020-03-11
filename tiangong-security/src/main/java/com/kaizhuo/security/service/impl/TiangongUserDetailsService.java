package com.kaizhuo.security.service.impl;

import com.kaizhuo.core.exception.AppException;
import com.kaizhuo.core.security.TiangongSecurityService;
import com.kaizhuo.core.security.TiangongSecurityUser;
import com.kaizhuo.security.error.SecurityError;
import com.kaizhuo.security.service.domain.TiangongUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @program: tiangong
 * @package: com.kaizhuo.security.auth.common
 * @description: UserDetailsService 实现
 * @author: miaochen
 * @create: 2020-02-29 12:00
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public class TiangongUserDetailsService implements UserDetailsService {

    @Autowired
    protected TiangongSecurityService tiangongSecurityService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        TiangongSecurityUser user = tiangongSecurityService.findFirstByUsername(s);
        if (user == null) {
            throw new AppException(SecurityError.USER_NOT_FOUNT);
        }
        return new TiangongUserDetails(user.getUsername(), user.getPassword());
    }
}
