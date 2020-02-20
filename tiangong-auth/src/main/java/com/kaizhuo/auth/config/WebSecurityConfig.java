package com.kaizhuo.auth.config;

import com.kaizhuo.auth.service.authenication.*;
import com.kaizhuo.auth.service.interfaces.UsernamePasswordUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @program: tiangong
 * @package: com.kaizhuo.auth.config
 * @description: 自定义配置
 * @author: miaochen
 * @create: 2020-02-14 18:55
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Autowired
    private UsernamePasswordUserDetailService usernamePasswordUserDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;


    /**
     * 登录页面相关配置
     * 配置自定义登录页面为index.html，后端用户名及密码验证为"/login"
     * 因为为自定义登录页面，配置"/index.html", "/login", "/resources/**", "/static/"不需要认证，其它请求需要认证。否则登录页面无法打开
     * 配置退出登录时，删除session和cookie，并自定义退出成功后的handler
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin()
                .loginPage("/index.html")
                .loginProcessingUrl("/login")
                .and()
                .logout().invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .logoutSuccessHandler(customLogoutSuccessHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/index.html", "/login", "/resources/**", "/static/**").permitAll()
                //任何请求
                .anyRequest()
                //需要安全验证
                .authenticated()
                ;
        // 自定义认证filter，支持./oauth/custom/token实现authorization_code认证
        http.addFilterAfter(externalAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 加入自定义的UsernamePasswordAuthenticationProvider，用于实现用户名和密码认证
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 定义认证的provider用于实现用户名和密码认证
        auth.authenticationProvider(new UsernamePasswordAuthenticationProvider(usernamePasswordUserDetailService));
        // 自定义provider用于实现自定义的登录认证
        auth.authenticationProvider(new CustomAuthenticationProvider());
    }


    @Bean
    public CustomAuthenticationProcessingFilter externalAuthenticationProcessingFilter() {
        // 自定义认证filter，需要实现CustomAuthenticationProcessingFilter和CustomerAuthenticationProvider
        // filter将过滤url并把认证信息塞入authentication作为CustomerAuthenticationProvider.authenticate的入参
        CustomAuthenticationProcessingFilter filter = new CustomAuthenticationProcessingFilter();

        // 默认自定义认证方式grant_type为authorization_code方式，如果直接返回内容，则需自定义success和fail handler
        //filter.setAuthenticationSuccessHandler(new CustomAuthenticationSuccessHandler());
        //filter.setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
