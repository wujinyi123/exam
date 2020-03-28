package com.system.exam.common;

import com.system.exam.domain.dto.user.UserDTO;

/**
 * UserSession工具
 */
public interface IUserSession {
    /**
     * 生成uuid(token)，并把用户存入Redis
     * 默认名字token
     * @param obj
     * @return
     */
    String saveUser(UserDTO obj);

    /**
     * 生成uuid(token)，并把用户存入Redis
     * @param obj
     * @param key
     * @return
     */
    String saveUser(UserDTO obj, String key);

    /**
     * 生成uuid(token)，并把用户存入Redis
     * 默认名字token
     * @param obj
     * @param time
     * @return
     */
    String saveUser(UserDTO obj, int time);

    /**
     * 生成uuid(token)，并把用户存入Redis
     * @param obj
     * @param key
     * @param time
     * @return
     */
    String saveUser(UserDTO obj, String key, int time);

    /**
     * 获取当前用户
     * 默认名字token
     * @return
     */
    UserDTO getUser();

    /**
     * 获取当前用户
     * @param key
     * @return
     */
    UserDTO getUser(String key);

    /**
     * 删除uuid(token)对应的对象
     * 默认名字token
     * @return
     */
    boolean deleteUser();

    /**
     * 删除uuid(token)对应的对象
     * @param key
     * @return
     */
    boolean deleteUser(String key);

    /**
     * 从cookie中获取uuid(token)
     * 默认名字token
     * @return
     */
    String getUserToken();

    /**
     * 从cookie中获取uuid(token)
     * @param key
     * @return
     */
    String getUserToken(String key);

    /**
     * 通过key和token获取用户信息
     * @param key
     * @param token
     * @return
     */
    UserDTO getUserByKeyToken(String key, String token);

    /**
     * 更新用户
     * @param key
     * @param obj
     * @return
     */
    boolean updateUser(String key, UserDTO obj);

}
