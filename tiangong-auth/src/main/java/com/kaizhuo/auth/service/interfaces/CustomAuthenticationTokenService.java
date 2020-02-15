package com.kaizhuo.auth.service.interfaces;

import com.kaizhuo.auth.service.authenication.CustomAuthenticationToken;

import java.util.Map;

/**
 * @program: tiangong
 * @package: com.kaizhuo.auth.service.interfaces
 * @description:
 * @author: miaochen
 * @create: 2020-02-14 22:07
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public interface CustomAuthenticationTokenService {
    /**
     * 获取 authentication token
     * @param clientId appId
     * @param params params
     * @return token
     */
    CustomAuthenticationToken createAuthenticationToken(String clientId, Map<String, String> params);
}
