package com.kaizhuo.auth.service.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.*;

/**
 * @program: tiangong
 * @package: com.kaizhuo.auth.service.jwt
 * @description: Token加密
 * @author: miaochen
 * @create: 2020-02-14 20:53
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public class TokenEnhancer implements org.springframework.security.oauth2.provider.token.TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        final Map<String, Object> additionalInfo = new HashMap<>(2);

        Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();
        if (oAuth2Authentication.isClientOnly()) {
            List<String> authorities = new ArrayList<>(1);
            authorities.add("admin");
            additionalInfo.put("authorities", authorities);

        } else {
            additionalInfo.put("userInfo", userAuthentication.getDetails());

            // 将authorities转换为string类型，便于json序列化
            Set<GrantedAuthority> rolesInfo = new HashSet<>(userAuthentication.getAuthorities());
            additionalInfo.put("authorities", rolesInfo.stream().map(auth -> auth.getAuthority()).toArray());
        }
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInfo);
        return oAuth2AccessToken;
    }
}
