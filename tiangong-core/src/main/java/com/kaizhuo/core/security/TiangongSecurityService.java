package com.kaizhuo.core.security;

public interface TiangongSecurityService {
    /**
     * 根据用户名，判断该URL是否有访问权限
     *
     * @param username 用户名
     * @param url      url
     * @return 是否有访问权限
     */
    boolean isUrlPermissionByName(String username, String url);

    /**
     * 根据用户名获取用户
     *
     * @param username 用户名
     * @return 用户
     */
    TiangongSecurityUser findFirstByUsername(String username);
}
