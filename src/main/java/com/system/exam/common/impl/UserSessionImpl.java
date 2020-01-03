package com.system.exam.common.impl;

import com.system.exam.common.IUserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * UserSession工具
 */
@Component
public class UserSessionImpl implements IUserSession {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private HttpServletRequest request;

    /**
     * 生成uuid(token)，并把用户存入Redis
     * 默认项目名字token
     * @param obj
     * @param <T>
     * @return
     */
    @Override
    public <T> String saveUser(T obj) {
        return saveUser(obj, 30);
    }

    /**
     * 生成uuid(token)，并把用户存入Redis
     * @param obj
     * @param projectName
     * @param <T>
     * @return
     */
    @Override
    public <T> String saveUser(T obj, String projectName) {
        return saveUser(obj, projectName, 30);
    }

    /**
     * 生成uuid(token)，并把用户存入Redis
     * 默认项目名字token
     * @param obj
     * @param time
     * @param <T>
     * @return
     */
    @Override
    public <T> String saveUser(T obj, int time) {
        return saveUser(obj, "token", time);
    }

    /**
     * 生成uuid(token)，并把用户存入Redis
     * @param obj
     * @param projectName
     * @param <T>
     * @return
     */
    @Override
    public <T> String saveUser(T obj, String projectName, int time) {
        String uuid = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(projectName+":"+uuid, obj, time, TimeUnit.MINUTES);
        return uuid;
    }

    /**
     * 获取当前用户
     * 默认项目名字token
     * @param <T>
     * @return
     */
    @Override
    public <T> T getUser() {
        return getUser("token");
    }

    /**
     * 获取当前用户
     * @param projectName
     * @param <T>
     * @return
     */
    @Override
    public <T> T getUser(String projectName) {
        String token = getUserToken(projectName);
        if (token != null) {
            return (T)redisTemplate.opsForValue().get(projectName + ":" + token);
        }
        return null;
    }

    /**
     * 删除uuid(token)对应的对象
     * 默认项目名字token
     * @param <T>
     * @return
     */
    @Override
    public <T> boolean deleteUser() {
        return deleteUser("token");
    }

    /**
     * 删除uuid(token)对应的对象
     * @param projectName
     * @param <T>
     * @return
     */
    @Override
    public <T> boolean deleteUser(String projectName) {
        String token = getUserToken(projectName);
        T t = (T)redisTemplate.opsForValue().get(projectName+":"+token);
        if (t == null) {
            return false;
        } else {
            redisTemplate.opsForValue().set(projectName+":"+token,null);
            return true;
        }
    }

    /**
     * 从cookie中获取uuid(token)
     * 默认项目名字token
     * @return
     */
    @Override
    public String getUserToken() {
        return getUserToken("token");
    }

    /**
     * 从cookie中获取uuid(token)
     * @param projectName
     * @return
     */
    @Override
    public String getUserToken(String projectName) {
        Cookie[] cookies = request.getCookies();
        String token = "";
        for (Cookie cookie : cookies) {
            if (projectName.equals(cookie.getName())) {
                token = cookie.getValue();
                break;
            }
        }
        return token;
    }

}
