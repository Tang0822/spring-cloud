package com.tjffy.learn.auth.filter;

import com.tjffy.learn.auth.entity.Group;
import com.tjffy.learn.auth.entity.Permission;
import com.tjffy.learn.auth.repositories.AuthRepository;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author jftang3
 */
@Component
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    private AuthRepository authRepository;

    private static Map<String, Collection<ConfigAttribute>> permissionMap = null;

    public MyInvocationSecurityMetadataSourceService(AuthRepository authRepository) {
        //使用注解方式的话，只能在构造函数执行完成后才能获得实例
        this.authRepository = authRepository;
        loadPermission();
    }

    private void loadPermission() {
        List<Permission> permissions = authRepository.findAll();
        permissionMap = new HashMap<>();
        for (Permission permission : permissions) {
            String key = permission.getMethod() + "-" + permission.getUrl();
            List<Group> groups = permission.getGroups();
            for (Group group : groups) {
                ConfigAttribute ca = new SecurityConfig(group.getName());
                if (permissionMap.containsKey(key)) {
                    Collection<ConfigAttribute> value = permissionMap.get(key);
                    value.add(ca);
                    permissionMap.put(key, value);
                } else {
                    Collection<ConfigAttribute> atts = new ArrayList<>();
                    atts.add(ca);
                    permissionMap.put(key, atts);
                }
            }
        }
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        // o 是一个URL，被用户请求的url。
        String url = ((FilterInvocation) o).getRequestUrl();
        String method = ((FilterInvocation) o).getHttpRequest().getMethod();
        int firstQuestionMarkIndex = url.indexOf("?");
        if (firstQuestionMarkIndex != -1) {
            url = url.substring(0, firstQuestionMarkIndex);
        }
        url = method + "-" + url;
        Iterator<String> ite = permissionMap.keySet().iterator();
        while (ite.hasNext()) {
            String resURL = ite.next();
            if (url.equals(resURL)) {
                return permissionMap.get(resURL);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
