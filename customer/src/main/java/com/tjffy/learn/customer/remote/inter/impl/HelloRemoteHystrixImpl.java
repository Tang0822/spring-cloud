package com.tjffy.learn.customer.remote.inter.impl;

import com.tjffy.learn.customer.remote.inter.HelloRemote;
import org.springframework.stereotype.Component;

/**
 * @author jftang3
 */
@Component
public class HelloRemoteHystrixImpl implements HelloRemote {

    @Override
    public String helloWord1(String name) {
        return "hello word " + name + " this is failed";
    }
}
