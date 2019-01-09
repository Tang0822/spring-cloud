package com.tjffy.learn.auth.service.impl;

import com.tjffy.learn.auth.entity.*;
import com.tjffy.learn.auth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author jftang3
 */
@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user != null) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            Group group = user.getGroup();
            List<Permission> permissions = user.getGroup().getPermissions();
            for (Permission permission : permissions) {
                GrantedAuthority grantedAuthority = new MyGrantedAuthority(permission.getUrl(), permission.getMethod(), permission.getIsCom(), permission.getPath());
                grantedAuthorities.add(grantedAuthority);
            }
            return new MyUserDetails(user.getId(), username, user.getPassword(), true, group.getId(), group.getType(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException(username + " do not exist!");
        }
    }
}