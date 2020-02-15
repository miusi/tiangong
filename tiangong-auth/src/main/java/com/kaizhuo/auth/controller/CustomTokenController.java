package com.kaizhuo.auth.controller;

import com.kaizhuo.auth.service.authenication.CustomAuthenticationToken;
import com.kaizhuo.auth.service.granter.CustomTokenGranter;
import com.kaizhuo.auth.service.interfaces.CustomAuthenticationTokenService;
import com.kaizhuo.auth.service.jwt.TokenServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.Map;

/**
 * @program: tiangong
 * @package: com.kaizhuo.auth.controller
 * @description:* 参考 TokenEndpoint 实现/auth/token方法
 *  * 用于第三方认证后直接办法token
 *  * 按时未支持refresh token刷新access token
 * @author: miaochen
 * @create: 2020-02-14 22:13
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Controller
@RequestMapping("/api/external/")
public class CustomTokenController {
    private ClientDetailsService clientDetailsService;
    private TokenServiceFactory tokenServiceFactory;
    private OAuth2RequestFactory oAuth2RequestFactory;
    private OAuth2RequestValidator oAuth2RequestValidator;
    private CustomAuthenticationTokenService customAuthenticationTokenService;

    @Autowired
    public CustomTokenController(
            ClientDetailsService clientDetailsService,
            TokenServiceFactory tokenServiceFactory,
            CustomAuthenticationTokenService customAuthenticationTokenService) {
        this.clientDetailsService = clientDetailsService;
        this.tokenServiceFactory = tokenServiceFactory;
        this.customAuthenticationTokenService = customAuthenticationTokenService;
    }


    /**
     * 经过认证的服务端获取token
     *
     * @param principal  通过认证token获取服务端信息, 目前clientId=weixin
     * @param parameters 认证的用户信息，包含 principal=userId, auth_type=weixin, scope=all, client_id=weixin, grant_type=third_party
     * @return jwt token
     */
    @RequestMapping(value = "token", method = RequestMethod.POST)
    public ResponseEntity<OAuth2AccessToken> getUserToken(Principal principal, @RequestBody Map<String, String> parameters) {

        if (!(principal instanceof Authentication)) {
            throw new InsufficientAuthenticationException("There is no client authentication. Try adding an appropriate authentication filter.");
        }
        String clientId = this.getClientId(principal);
        ClientDetails authenticatedClient = this.clientDetailsService.loadClientByClientId(clientId);
        TokenRequest tokenRequest = this.oAuth2RequestFactory.createTokenRequest(parameters, authenticatedClient);
        this.validate(clientId, authenticatedClient, tokenRequest);

        CustomAuthenticationToken authentication = customAuthenticationTokenService.createAuthenticationToken(clientId, parameters);

        CustomTokenGranter tokenGranter = new CustomTokenGranter(
                tokenServiceFactory.customJwtTokenService(), clientDetailsService, authentication);

        OAuth2AccessToken token = tokenGranter.grant(tokenRequest.getGrantType(), tokenRequest);

        if (token == null) {
            throw new UnsupportedGrantTypeException("Unsupported grant type: " + tokenRequest.getGrantType());
        } else {
            return this.getResponse(token);
        }
    }

    private ResponseEntity<OAuth2AccessToken> getResponse(OAuth2AccessToken accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        return new ResponseEntity<>(accessToken, headers, HttpStatus.OK);
    }


    private void validate(String clientId, ClientDetails authenticatedClient, TokenRequest tokenRequest) {
        if (clientId != null && !clientId.isEmpty() && !clientId.equals(tokenRequest.getClientId())) {
            throw new InvalidClientException("Given client ID does not match authenticated client");
        }

        if (authenticatedClient != null) {
            this.oAuth2RequestValidator.validateScope(tokenRequest, authenticatedClient);
        }
    }

    private String getClientId(Principal principal) {
        Authentication client = (Authentication) principal;
        if (!client.isAuthenticated()) {
            throw new InsufficientAuthenticationException("The client is not authenticated.");
        } else {
            String clientId = client.getName();
            if (client instanceof OAuth2Authentication) {
                clientId = ((OAuth2Authentication) client).getOAuth2Request().getClientId();
            }
            return clientId;
        }
    }
}
