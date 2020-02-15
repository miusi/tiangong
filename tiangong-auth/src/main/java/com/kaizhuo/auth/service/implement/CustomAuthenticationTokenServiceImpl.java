package com.kaizhuo.auth.service.implement;

import com.kaizhuo.auth.service.authenication.CustomAuthenticationToken;
import com.kaizhuo.auth.service.interfaces.CustomAuthenticationTokenService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: tiangong
 * @package: com.kaizhuo.auth.service.implement
 * @description:
 * @author: miaochen
 * @create: 2020-02-14 22:08
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Service
public class CustomAuthenticationTokenServiceImpl implements CustomAuthenticationTokenService {

    @Override
    public CustomAuthenticationToken createAuthenticationToken(String clientId, Map<String, String> params) {
        if (!params.containsKey("principal") || !params.containsKey("auth_type")) {
            return null;
        }

        if (params.get("auth_type").equals("auth_finished")) {

            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("user"));
            Map<String, Object> detail = new HashMap<>(1);
            detail.put("principal", params.get("principal"));

            CustomAuthenticationToken token = new CustomAuthenticationToken(
                    params.get("auth_type"), params.get("username"), null, null, authorities);
            token.setDetails(detail);
            return token;
        }

        return null;
    }
}
