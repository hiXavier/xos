package com.xavier.xos.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @Author wuyanfeng
 * @Description
 * @Date 2019/9/2 14:38
 * 启用方法级别的权限认证
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 用来配置拦截保护的请求，
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //X-Frame-Options字段去掉了
        http.headers().frameOptions().disable();
        // restful具有先天的防范csrf攻击，所以关闭这功能
        http.csrf().disable()
                // 默认允许所有的请求通过，后序我们通过方法注解的方式来粒度化控制权限
                .authorizeRequests().anyRequest().permitAll()
                .and()
                // 添加属于我们自己的过滤器，注意因为我们没有开启formLogin()，所以UsernamePasswordAuthenticationFilter根本不会被调用
//                .addFilterAt(ssoFilter(), UsernamePasswordAuthenticationFilter.class)
                // 前后端分离本身就是无状态的，所以我们不需要cookie和session这类东西。所有的信息都保存在一个token之中。
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
//                .accessDeniedHandler(accessDeniedHandler)
        ;
    }

    /**
     * 用来配置Fileter链
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**", "/webjars/**/*");
    }


}
