package com.kaizhuo.core.security;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: miaochen
 * \* @Date: 2020/2/25
 * \* @Time: 15:09
 * \* To change this template use File | Settings | File Templates.
 * \* @Description:
 * \
 */
public class TiangongSecurityUtil {
    private static TiangongSecurityContext context = TiangongSecurityContext.getInstance();

    /**
     * 设置登录信息
     *
     * @param username 用户名
     * @param userId   用户id
     */
    public static void setLoginInfo(String username, Long userId) {
        context.setLoginInfo(username, userId);
    }

    /**
     * 获取用户名
     *
     * @return 用户名
     */
    public static String getUsername() {
        return context.getUsername();
    }

    /**
     * 获取用户id
     *
     * @return 用户id
     */
    public static Long getUserId() {
        return context.getUserId();
    }

    /**
     * 登出
     */
    public static void logout() {
        context.logout();
    }
}
