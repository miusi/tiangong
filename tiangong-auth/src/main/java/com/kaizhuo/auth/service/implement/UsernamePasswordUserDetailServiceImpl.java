package com.kaizhuo.auth.service.implement;


import com.kaizhuo.auth.service.interfaces.UsernamePasswordUserDetailService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: tiangong
 * @package: com.kaizhuo.auth.service.implement
 * @description: 用户密码操作实现
 * @author: miaochen
 * @create: 2020-02-14 19:16
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Service
public class UsernamePasswordUserDetailServiceImpl implements UsernamePasswordUserDetailService {
    @Override
    public boolean verifyCredential(String name, String password) {
        //TODO 用户名密码验证登录 数据库操作
        return true;
    }

    @Override
    public Object getUserDetails(String username) {
        //TODO 额外的配置，包括权限
        List<GrantedAuthority> authorities=new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("user"));
        Map<String, Object> detail = new HashMap<>(2);
        detail.put("name", username);
        detail.put("roles", authorities);
        return detail;
    }
}
