package com.tjffy.learn.authuser.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 拦截器配置，用于装配拦截器
 * @author root
 *         Created on 2018/3/9
 */
@Component
@Slf4j
public class InterceptorConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("---------------------》》》》进入拦截器");
        //注册拦截器
        InterceptorRegistration ir = registry.addInterceptor(new LoginAndPermissionInterceptor());
//        配置拦截的路径
        ir.addPathPatterns("/**");
    }
}
