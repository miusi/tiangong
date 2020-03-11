package com.kaizhuo.security.config;

import com.kaizhuo.security.service.authentication.filter.OptionRequestFilter;
import com.kaizhuo.security.service.authentication.provider.TiangongAuthenticationProvider;
import com.kaizhuo.security.service.authentication.filter.TiangongAuthenticationProcessingFilter;
import com.kaizhuo.security.service.authentication.handler.TiangongLogoutSuccessHandler;
import com.kaizhuo.security.service.impl.TiangongUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;

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
    private TiangongLogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private TiangongAuthenticationProvider tiangongAuthenticationProvider;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private TiangongUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;
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
        http    //禁用跨站csrf攻击防御
                .csrf().disable()
                //未登录
                //未登录
                //.httpBasic().authenticationEntryPoint(authenticationEntryPoint)
                //.and()
                .formLogin()
                //用户未登录时，访问任何资源都转跳到该路径，即登录页面,DefaultLoginPageGeneratingFilter
                .loginPage("/index.html")
                //登录表单form中action的地址，也就是处理认证请求的路径
                .loginProcessingUrl("/login")
                //登录成功
                //.successHandler(authenticationSuccessHandler)
                //登录失败
                //.failureHandler(authenticationFailureHandler)
                .and()
                .authorizeRequests()
                //不需要通过登录验证就可以被访问的资源路径
                .antMatchers("/index.html", "/login", "/resources/**", "/static/**")
                .permitAll()
                //rbac权限
                .anyRequest()
                .authenticated()
                //.access("@tiangongAuthorityService.hasPermission(request,authentication)")
                //跨域
//                .and()
//                .headers().addHeaderWriter(new StaticHeadersWriter(Arrays.asList(
//                new Header("Access-control-Allow-Origin", "*"),
//                new Header("Access-Control-Expose-Headers", SecurityProperties.authKey))))

//                .and()
//                .cors()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                //.logoutUrl()
                //注销成功
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll();

        http.addFilterAfter(new OptionRequestFilter(), CsrfFilter.class);
        //记住我
        http.rememberMe().rememberMeParameter("rememberMe").userDetailsService(userDetailsService).tokenValiditySeconds(securityProperties.getTokenExpiredTime());
        //无权限
        //http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);

        //JWT
        http
                //.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                //直接使用内部的OAUTH_TOKEN_URL
                .addFilterAfter(externalAuthenticationProcessingFilter(),UsernamePasswordAuthenticationFilter.class)
                //.addFilterAfter(jwtAuthenticationRefreshFilter, UsernamePasswordAuthenticationFilter.class)
        ;

        /***跨域*/
//        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry
//                = http.authorizeRequests();
//        registry.requestMatchers(CorsUtils::isPreFlightRequest).permitAll();

    }


    @Bean
    public TiangongAuthenticationProcessingFilter externalAuthenticationProcessingFilter() {
        // 自定义认证filter，需要实现CustomAuthenticationProcessingFilter和CustomerAuthenticationProvider
        // filter将过滤url并把认证信息塞入authentication作为CustomerAuthenticationProvider.authenticate的入参
        TiangongAuthenticationProcessingFilter filter = new TiangongAuthenticationProcessingFilter();

        // 默认自定义认证方式grant_type为authorization_code方式，如果直接返回内容，则需自定义success和fail handler
        // filter.setAuthenticationSuccessHandler(new CustomAuthenticationSuccessHandler());
        // filter.setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());

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
