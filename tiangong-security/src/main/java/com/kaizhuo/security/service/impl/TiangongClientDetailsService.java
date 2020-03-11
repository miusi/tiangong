package com.kaizhuo.security.service.impl;

import com.kaizhuo.security.modela.app.AppCredential;
import com.kaizhuo.security.modela.app.AppCredentialDetail;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

/**
 * @program: tiangong
 * @package: com.kaizhuo.security.auth.common
 * @description:
 * @author: godric
 * @create: 2020/3/11 0011
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: godric
 **/
@Service
@Primary
public class TiangongClientDetailsService implements ClientDetailsService {
    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        if ("weixin".equals(s) || "app".equals(s) || "mini_app".equals(s) || "global".equals(s)) {
            AppCredential credential = new AppCredential();
            credential.setAppId(s);
            credential.setAppSecret("testpassword");
            credential.setGrantTypes("authorization_code,client_credentials,password,refresh_token,mini_app");
            credential.setAccessExpireTime(3600 * 24);
            credential.setRefreshExpireTime(3600 * 24 * 30);
            credential.setRedirectUrl("http://localhost:3006,http://localhost:3006/auth,http://localhost:8000,http://localhost:8000/auth,http://admin.awesome-coder.com/auth");
            credential.setScopes("all");
            return new AppCredentialDetail(credential);
        }
        return null;
    }
}
