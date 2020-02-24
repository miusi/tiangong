package com.kaizhuo.usercenter.config;

import com.kaizhuo.usercenter.bo.BaseUserDetails;
import com.kaizhuo.usercenter.dao.BaseUser;
import com.kaizhuo.usercenter.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

/**
 * @program: tiangong
 * @package: com.kaizhuo.usercenter.config
 * @description:
 * @author: miaochen
 * @create: 2020-02-23 14:42
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .anyRequest().permitAll().and().logout().permitAll();//配置不需要登录验证

    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(
                        "/v2/api-docs",
                        "/swagger-resources/**",
                        "/swagger-ui.html**",
                        "/webjars/**")
        .antMatchers("/h2-console/**");
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService(){

        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
                Optional<BaseUser> optionalUser=userRepository.findByUsername(s);
                if(optionalUser.isPresent()){
                    return new BaseUserDetails(optionalUser.get());
                }
                throw new UsernameNotFoundException("用户名或密码错误");
            }
        };
    }
}
