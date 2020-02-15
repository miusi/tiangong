package com.kaizhuo.ironman.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

/**
 * @program: tiangong
 * @package: com.kaizhuo.ironman.config
 * @description: Feign Oauth2.0拦截器
 * @author: miaochen
 * @create: 2020-02-15 13:25
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public class AuthFeignConfigInterceptor implements RequestInterceptor {
    private static String TokenHeader = "authorization";
    private static String AccessTokenPrefix = "bearer ";

    private static Logger logger = LoggerFactory.getLogger(AuthFeignConfigInterceptor.class);

    @Autowired
    private ClientCredentialsResourceDetails clientCredentialsResourceDetails;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(TokenHeader, AccessTokenPrefix + getAccessTokenValue());
    }

    private String getAccessTokenValue() {
        try {
            logger.info("auth服务中获取basic access token");
            OAuth2AccessToken accessToken = this.getAccessTokenFromAuth();
            return accessToken.getValue();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return this.getAccessTokenFromAuth().getValue();
        }
    }

    private OAuth2AccessToken getAccessTokenFromAuth() {
        ClientCredentialsAccessTokenProvider provider = new ClientCredentialsAccessTokenProvider();
        return provider.obtainAccessToken(clientCredentialsResourceDetails, new DefaultAccessTokenRequest());
    }
}
