package com.kaizhuo.auth.service.jwt;

import com.kaizhuo.auth.service.authenication.CustomAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @program: tiangong
 * @package: com.kaizhuo.auth.service.jwt
 * @description: 自定义Token加密
 * @author: miaochen
 * @create: 2020-02-14 21:03
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public class CustomTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        final Map<String, Object> additionalInfo = new HashMap<>();
        Set<GrantedAuthority> rolesInfo = new HashSet<>();

        Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();

        // client credential认证，加入管理员角色
        if (oAuth2Authentication.isClientOnly()) {
            rolesInfo.add(new SimpleGrantedAuthority("admin"));
        }

        // 自定义认证，增加detail
        if (CustomAuthenticationToken.class.isAssignableFrom(userAuthentication.getClass())) {
            rolesInfo.addAll(userAuthentication.getAuthorities());
            additionalInfo.put("userInfo", userAuthentication.getDetails());
        }

        // 加入角色
        additionalInfo.put("authorities", rolesInfo.stream().map(auth -> auth.getAuthority()).toArray());
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInfo);
        return oAuth2AccessToken;
    }
}
