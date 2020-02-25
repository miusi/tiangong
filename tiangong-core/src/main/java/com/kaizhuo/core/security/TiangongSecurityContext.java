package com.kaizhuo.core.security;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: miaochen
 * \* @Date: 2020/2/25
 * \* @Time: 15:10
 * \* To change this template use File | Settings | File Templates.
 * \* @Description:
 * \
 */
public class TiangongSecurityContext {
    private static volatile TiangongSecurityContext instance;

    public static TiangongSecurityContext getInstance() {
        if (instance == null) {
            synchronized (TiangongSecurityContext.class) {
                if (instance == null) {
                    instance = new TiangongSecurityContext();
                }
            }
        }
        return instance;
    }

    private ThreadLocal<String> username = new ThreadLocal<>();
    private ThreadLocal<Long> userId = new ThreadLocal<>();

    /**
     * 设置登录信息
     *
     * @param username 用户名
     * @param userId   用户id
     */
    public void setLoginInfo(String username, Long userId) {
        this.username.set(username);
        this.userId.set(userId);
    }

    /**
     * 获取用户名
     *
     * @return 用户名
     */
    public String getUsername() {
        return this.username.get();
    }

    /**
     * 获取用户id
     *
     * @return 用户id
     */
    public Long getUserId() {
        return this.userId.get();
    }

    /**
     * 登出
     */
    public void logout() {
        this.username.remove();
        this.userId.remove();
    }
}
