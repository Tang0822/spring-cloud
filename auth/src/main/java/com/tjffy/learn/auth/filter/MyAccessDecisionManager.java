package com.tjffy.learn.auth.filter;

import com.tjffy.learn.auth.entity.MyGrantedAuthority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author jftang3
 */
@Component
@Slf4j
public class MyAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        if (collection == null) {
            return;
        }
        Iterator<ConfigAttribute> ite = collection.iterator();
        while (ite.hasNext()) {
            ConfigAttribute ca = ite.next();
            String group = ((SecurityConfig) ca).getAttribute();
            log.info("---------------------------------》》》》 authentication size" + authentication.getAuthorities().size());
            // ga 为用户所被赋予的权限。 needRole 为访问相应的资源应该具有的权限。
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                MyGrantedAuthority myGrantedAuthority = (MyGrantedAuthority) ga;
                if (group.trim().equals(myGrantedAuthority.getGroup())) {
                    return;
                }
            }
        }

        throw new AccessDeniedException("无权限");

        /*HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
        if ("anonymousUser".equals(authentication.getPrincipal())
                || matchers("/", request)
                || matchers("/index.html", request)
                || matchers("/favicon.ico", request)
                || matchers("/login.html", request)
                || matchers("/index", request)
                || matchers("/getCheckCode", request)
                || matchers("/resPassword", request)
                || matchers("/test/**", request)) {
            log.info("免验证");
            return;
        } else {
            String url;
            String method;
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (ga instanceof MyGrantedAuthority) {
                    MyGrantedAuthority myGrantedAuthority = (MyGrantedAuthority) ga;
                    url = myGrantedAuthority.getAuthority().split(";")[0];
                    method = myGrantedAuthority.getAuthority().split(";")[1];
                    if (matchers(url, request)) {
                        if (method.equals(request.getMethod()) || "ALL".equals(method)) {
                            return;
                        }
                    }
                }
            }
        }
        log.info("没有权限:"+request.getServletPath());
        throw new AccessDeniedException("no right");*/
    }

    private boolean matchers(String url, HttpServletRequest request) {
        AntPathRequestMatcher matcher = new AntPathRequestMatcher(url);
        if (matcher.matches(request)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
