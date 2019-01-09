package com.tjffy.learn.auth.conf;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * @author jftang3
 */
public class SecurityWebInitializer extends AbstractSecurityWebApplicationInitializer {
    public SecurityWebInitializer() {
        super(WebSecurityConfig.class);
    }
}
