package com.tjffy.learn.zuul.config;

import com.tjffy.learn.zuul.filter.MyFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jftang3
 */
@Configuration
public class BaseConfig {

    @Bean
    public MyFilter myFilter() {
        return new MyFilter();
    }
}
