package com.tjffy.learn.authuser.filter;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjffy.learn.authuser.dao.JedisDao;
import com.tjffy.learn.authuser.uti.SerializeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author jftang3
 */
@Slf4j
@Component
public class LoginAndPermissionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 根据sessionId查询redis中寸的session信息
        String sessionId = httpServletRequest.getHeader("sessionId");
        String url = httpServletRequest.getRequestURI();
        log.info("----------------------------------》》》》 当前请求路径：{}", url);
        log.info("----------------------------------》》》》 当前的sessionId: {}", sessionId);
        String key = "spring:session:sessions:" + sessionId;
        String filed = "sessionAttr:SPRING_SECURITY_CONTEXT";
        ServletContext servletContext = httpServletRequest.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        JedisDao jedisDao = ctx.getBean(JedisDao.class);
        byte[] hget = jedisDao.getHashFromRedis(key, filed);
        SecurityContextImpl sc;
        Authentication authentication = null;
        // 反序列化
        if (hget != null) {
            Object obj = SerializeUtil.unSerizlize(hget);
            sc = (SecurityContextImpl) obj;
            authentication = sc != null ? sc.getAuthentication() : null;
        }
        if (hget == null || authentication == null || "anonymousUser".equals(authentication.getPrincipal())) {
            log.info("----------------------------------》》》》 用户未登录");
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json; charset=utf-8");
            PrintWriter out = httpServletResponse.getWriter();
            JSONObject object = new JSONObject();
            object.put("status", 401);
            object.put("message", "用户未登录");
            String resJson = (new ObjectMapper()).writeValueAsString(object);
            out.write(resJson);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
