package com.system.exam.common.impl;

import com.system.exam.common.IUserSession;
import com.system.exam.domain.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
     * 默认名字token
     * @param obj
     * @param <T>
     * @return
     */
    @Override
    public String saveUser(UserDTO obj) {
        return saveUser(obj, 30);
    }

    /**
     * 生成uuid(token)，并把用户存入Redis
     * @param obj
     * @param key
     * @param <T>
     * @return
     */
    @Override
    public String saveUser(UserDTO obj, String key) {
        return saveUser(obj, key, 30);
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
    public String saveUser(UserDTO obj, int time) {
        return saveUser(obj, "token", time);
    }

    /**
     * 生成uuid(token)，并把用户存入Redis
     * @param obj
     * @param key
     * @param <T>
     * @return
     */
    @Override
    public String saveUser(UserDTO obj, String key, int time) {
        String uuid = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(key+":"+uuid, obj, time, TimeUnit.MINUTES);
        return uuid;
    }

    /**
     * 获取当前用户
     * 默认项目名字token
     * @param <T>
     * @return
     */
    @Override
    public UserDTO getUser() {
        return getUser("token");
    }

    /**
     * 获取当前用户
     * @param key
     * @param <T>
     * @return
     */
    @Override
    public UserDTO getUser(String key) {
        String token = getUserToken(key);
        UserDTO t = null;
        if (!StringUtils.isEmpty(token)) {
            t = (UserDTO)redisTemplate.opsForValue().get(key + ":" + token);
        } else {
            t = new UserDTO();
        }
        return t;
    }

    /**
     * 删除uuid(token)对应的对象
     * 默认项目名字token
     * @param <T>
     * @return
     */
    @Override
    public boolean deleteUser() {
        return deleteUser("token");
    }

    /**
     * 删除uuid(token)对应的对象
     * @param key
     * @param <T>
     * @return
     */
    @Override
    public boolean deleteUser(String key) {
        String token = getUserToken(key);
        UserDTO t = (UserDTO)redisTemplate.opsForValue().get(key+":"+token);
        if (t == null) {
            return false;
        } else {
            redisTemplate.opsForValue().set(key+":"+token,null);
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
     * @param key
     * @return
     */
    @Override
    public String getUserToken(String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies==null) {
            cookies = new Cookie[]{};
        }
        String token = "";
        for (Cookie cookie : cookies) {
            if (key.equals(cookie.getName())) {
                token = cookie.getValue();
                break;
            }
        }
        return token;
    }

    /**
     * 通过key和token获取用户信息
     * @param key
     * @param token
     * @param <T>
     * @return
     */
    public UserDTO getUserByKeyToken(String key, String token) {
        return (UserDTO)redisTemplate.opsForValue().get(key + ":" + token);
    }

    /**
     * 更新用户
     * @param key
     * @param obj
     * @param <T>
     * @return
     */
    @Override
    public boolean updateUser(String key, UserDTO obj) {
        String token = getUserToken(key);
        if (token != null) {
            redisTemplate.opsForValue().set(key + ":" + token, obj);
            return true;
        } else {
            return false;
        }
    }

}
