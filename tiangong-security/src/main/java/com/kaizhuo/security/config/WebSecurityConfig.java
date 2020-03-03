package com.kaizhuo.security.config;

import com.kaizhuo.security.auth.common.*;
import com.kaizhuo.security.auth.jwt.JwtAuthenticationProvider;
import com.kaizhuo.security.auth.jwt.JwtAuthenticationRefreshFilter;
import com.kaizhuo.security.auth.jwt.JwtAuthenticationSuccessHandler;
import com.kaizhuo.security.auth.jwt.JwtAuthenticationTokenFilter;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
    private TiangongAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private JwtAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private TiangongAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private TiangongLogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private TiangongAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvier;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private TiangongUserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private JwtAuthenticationRefreshFilter jwtAuthenticationRefreshFilter;

    /**
     * 定义认证用户信息获取来源，密码校验规则等
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(jwtAuthenticationProvier);
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
        http.csrf().disable()
                //未登录
                .httpBasic().authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .authorizeRequests()
                //rbac权限
                .anyRequest()
                .access("@tiangongAuthorityService.hasPermission(request,authentication)")
                //跨域
                .and()
                .headers().addHeaderWriter(new StaticHeadersWriter(Arrays.asList(
                new Header("Access-control-Allow-Origin", "*"),
                new Header("Access-Control-Expose-Headers", SecurityProperties.authKey))))
                .and()

                .formLogin()
                //登录成功
                .successHandler(authenticationSuccessHandler)
                //登录失败
                .failureHandler(authenticationFailureHandler)
                .permitAll()
                .and()
                .cors()
                .and()
                .logout()
                //注销成功
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll();
        http.addFilterAfter(new OptionRequestFilter(), CsrfFilter.class);
        //记住我
        http.rememberMe().rememberMeParameter("rememberMe").userDetailsService(userDetailsService).tokenValiditySeconds(securityProperties.getTokenExpiredTime());
        //无权限
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);

        //JWT
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(jwtAuthenticationRefreshFilter, UsernamePasswordAuthenticationFilter.class);

        /***跨域*/
//        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry
//                = http.authorizeRequests();
//        registry.requestMatchers(CorsUtils::isPreFlightRequest).permitAll();

    }
}
