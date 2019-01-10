package com.tjffy.learn.authuser.controller;

import com.tjffy.learn.authuser.entity.User;
import com.tjffy.learn.authuser.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jftang3
 */
@RestController
@RequestMapping(name = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(name = "/list", method = RequestMethod.GET)
    public Map<String, Object> userList(String userName, Pageable pageable) {
        Map<String, Object> result = new HashMap<>();
        Page<User> users = userRepository.findByUserNameLike(userName, pageable);
        result.put("status", 200);
        result.put("message", "请求成功");
        result.put("date", users);
        return result;
    }
}
