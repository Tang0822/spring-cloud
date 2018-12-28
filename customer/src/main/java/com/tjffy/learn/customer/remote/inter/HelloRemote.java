package com.tjffy.learn.customer.remote.inter;

import com.tjffy.learn.customer.remote.inter.impl.HelloRemoteHystrixImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author jftang3
 */
@FeignClient(name = "producer", fallback = HelloRemoteHystrixImpl.class)
public interface HelloRemote {

    /**
     * 调用生产方法
     *
     * @param name 参数名
     * @return
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String helloWord(@RequestParam(value = "name") String name);
}
