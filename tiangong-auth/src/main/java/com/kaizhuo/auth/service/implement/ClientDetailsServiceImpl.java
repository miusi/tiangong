package com.kaizhuo.auth.service.implement;

import com.kaizhuo.auth.model.app.AppCredential;
import com.kaizhuo.auth.model.app.AppCredentialDetail;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

/**
 * @program: tiangong
 * @package: com.kaizhuo.auth.service.implement
 * @description: client取数实现
 * @author: miaochen
 * @create: 2020-02-14 20:46
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Service
@Primary
public class ClientDetailsServiceImpl implements ClientDetailsService {
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
