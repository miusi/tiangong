package com.kaizhuo.auth.service.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @program: tiangong
 * @package: com.kaizhuo.auth.service.jwt
 * @description:
 * @author: miaochen
 * @create: 2020-02-14 20:50
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Service
public class TokenServiceFactory {
    private TokenKeyConfig tokenKeyConfig;
    private ClientDetailsService clientDetailsService;

    @Autowired
    public TokenServiceFactory(TokenKeyConfig tokenKeyConfig, ClientDetailsService clientDetailsService) {
        this.tokenKeyConfig = tokenKeyConfig;
        this.clientDetailsService = clientDetailsService;
    }

    @Bean
    @Primary
    public AuthorizationServerTokenServices JwtTokenService() {
        final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));

        return defaultTokenService(tokenEnhancerChain);
    }

    @Bean
    public AuthorizationServerTokenServices customJwtTokenService() {
        final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(new CustomTokenEnhancer(), accessTokenConverter()));
        return defaultTokenService(tokenEnhancerChain);
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {

        //converter设置
        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //自定义
        converter.setAccessTokenConverter(new CustomAccessTokenConverter());

        final KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
                new ClassPathResource(tokenKeyConfig.getPath()), tokenKeyConfig.getPassword().toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair(tokenKeyConfig.getAlias()));

        return converter;
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new TokenEnhancer();
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    /**
     * 默认的token服务
     *
     * @param tokenEnhancerChain
     * @return
     */
    private AuthorizationServerTokenServices defaultTokenService(TokenEnhancerChain tokenEnhancerChain) {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setTokenEnhancer(tokenEnhancerChain);
        defaultTokenServices.setClientDetailsService(clientDetailsService);
        return defaultTokenServices;
    }
}