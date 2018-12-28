package com.tjffy.learn.customer.controller;

import com.tjffy.learn.customer.remote.inter.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jftang3
 */
@RestController
public class CustomerController {

    @Autowired
    private HelloRemote helloRemote;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String helloWord(String name) {
        return helloRemote.helloWord1(" customer1 " + name);
    }
}
