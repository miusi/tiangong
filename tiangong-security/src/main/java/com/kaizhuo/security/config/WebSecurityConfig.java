package com.kaizhuo.security.config;

import com.kaizhuo.security.service.authentication.TiangongAuthenticationEntryPoint;
import com.kaizhuo.security.service.authentication.filter.OptionRequestFilter;
import com.kaizhuo.security.service.authentication.filter.TiangongAuthenticationRefreshFilter;
import com.kaizhuo.security.service.authentication.filter.TiangongAuthenticationTokenFilter;
import com.kaizhuo.security.service.authentication.handler.TiangongAccessDeniedHandler;
import com.kaizhuo.security.service.authentication.handler.TiangongAuthenticationFailureHandler;
import com.kaizhuo.security.service.authentication.handler.TiangongAuthenticationSuccessHandler;
import com.kaizhuo.security.service.authentication.provider.TiangongAuthenticationProvider;
import com.kaizhuo.security.service.authentication.filter.TiangongAuthenticationProcessingFilter;
import com.kaizhuo.security.service.authentication.handler.TiangongLogoutSuccessHandler;
import com.kaizhuo.security.service.impl.TiangongUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

import java.util.Arrays;
import java.util.List;

/**
 * @program: tiangong
 * @package: com.kaizhuo.security.config
 * @description: 配置
 * @author: miaochen
 * @create: 2020-02-29 13:49
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TiangongAuthenticationEntryPoint tiangongAuthenticationEntryPoint;

    @Autowired
    private TiangongLogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private TiangongAuthenticationProvider tiangongAuthenticationProvider;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private TiangongUserDetailsService userDetailsService;

    @Autowired
    private TiangongAuthenticationSuccessHandler tiangongAuthenticationSuccessHandler;

    @Autowired
    private TiangongAuthenticationFailureHandler tiangongAuthenticationFailureHandler;

    @Autowired
    private TiangongAccessDeniedHandler tiangongAccessDeniedHandler;

    @Autowired
    private TiangongAuthenticationTokenFilter tiangongAuthenticationTokenFilter;

    @Autowired
    private TiangongAuthenticationRefreshFilter tiangongAuthenticationRefreshFilter;

    /**
     * 定义认证用户信息获取来源，密码校验规则等
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(tiangongAuthenticationProvider);
    }

    /**
     * 配置哪些页面不需要认证
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        List<String> noAuthPath = securityProperties.getNoAuthPaths();
        web.ignoring().antMatchers(noAuthPath.toArray(new String[0]));

    }

    /**
     * 定义安全策略
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //TODO OAUTH2.0
        http    //禁用跨站csrf攻击防御
                .csrf().disable()
                //未登录
                .httpBasic().authenticationEntryPoint(tiangongAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                //rbac权限
                .anyRequest()
                //.authenticated()
                .access("@tiangongAuthorityService.hasPermission(request,authentication)")

                //跨域
                .and()
                .headers().addHeaderWriter(new StaticHeadersWriter(Arrays.asList(
                new Header("Access-control-Allow-Origin", "*"),
                new Header("Access-Control-Expose-Headers", SecurityProperties.authKey))))
                .and()
                .addFilterAfter(new OptionRequestFilter(), CsrfFilter.class)
                .formLogin()
                //登录成功
                .successHandler(tiangongAuthenticationSuccessHandler)
                //登录失败
                .failureHandler(tiangongAuthenticationFailureHandler)
                .and()
                .cors()
                .and()
                .logout()
                //注销成功
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll();
        //记住我
        http.rememberMe().rememberMeParameter("rememberMe").userDetailsService(userDetailsService).tokenValiditySeconds(securityProperties.getTokenExpiredTime());
        //无权限
        http.exceptionHandling().accessDeniedHandler(tiangongAccessDeniedHandler);
        //JWT
        http
                .addFilterBefore(tiangongAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                //直接使用内部的OAUTH_TOKEN_URL
                //.addFilterAfter(externalAuthenticationProcessingFilter(),UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(tiangongAuthenticationRefreshFilter, UsernamePasswordAuthenticationFilter.class)
        ;

        /***跨域*/
//        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry
//                = http.authorizeRequests();
//        registry.requestMatchers(CorsUtils::isPreFlightRequest).permitAll();

    }
}
