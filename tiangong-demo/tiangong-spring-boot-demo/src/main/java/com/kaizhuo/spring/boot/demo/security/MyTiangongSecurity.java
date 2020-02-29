package com.kaizhuo.spring.boot.demo.security;

import com.kaizhuo.component.rbac.dao.UserDao;
import com.kaizhuo.component.rbac.model.domain.User;
import com.kaizhuo.core.security.TiangongSecurityService;
import com.kaizhuo.core.security.TiangongSecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: tiangong
 * @package: com.kaizhuo.spring.boot.demo.security
 * @description: 自定义实现security
 * @author: miaochen
 * @create: 2020-02-29 20:58
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Service
public class MyTiangongSecurity implements TiangongSecurityService {

    @Autowired
    private UserDao userDao;


    /**
     * 根据用户名和请求路径，判断是否有访问权限
     *
     * @param username 用户名
     * @param url      请求路径
     * @return 是否有访问权限
     */
    @Override
    public boolean isUrlPermissionByName(String username, String url) {
        //todo 判断权限
        return true;
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    @Override
    public TiangongSecurityUser findFirstByUsername(String username) {
        TiangongSecurityUser securityUser=new TiangongSecurityUser();
        // 此处应根据自身业务情况进行
        User user =userDao.findFirstByUsername(username);
        if(user!=null){
            securityUser.setUsername(user.getUsername());
            securityUser.setPassword(user.getPassword());
        }
        return securityUser;
    }
}
