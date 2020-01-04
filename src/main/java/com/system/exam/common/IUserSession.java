package com.system.exam.common;

/**
 * UserSession工具
 */
public interface IUserSession {
    /**
     * 生成uuid(token)，并把用户存入Redis
     * 默认名字token
     * @param obj
     * @param <T>
     * @return
     */
    <T> String saveUser(T obj);

    /**
     * 生成uuid(token)，并把用户存入Redis
     * @param obj
     * @param key
     * @param <T>
     * @return
     */
    <T> String saveUser(T obj, String key);

    /**
     * 生成uuid(token)，并把用户存入Redis
     * 默认名字token
     * @param obj
     * @param time
     * @param <T>
     * @return
     */
    <T> String saveUser(T obj, int time);

    /**
     * 生成uuid(token)，并把用户存入Redis
     * @param obj
     * @param key
     * @param time
     * @param <T>
     * @return
     */
    <T> String saveUser(T obj, String key, int time);

    /**
     * 获取当前用户
     * 默认名字token
     * @param <T>
     * @return
     */
    <T> T getUser();

    /**
     * 获取当前用户
     * @param key
     * @param <T>
     * @return
     */
    <T> T getUser(String key);

    /**
     * 删除uuid(token)对应的对象
     * 默认名字token
     * @param <T>
     * @return
     */
    <T> boolean deleteUser();

    /**
     * 删除uuid(token)对应的对象
     * @param key
     * @param <T>
     * @return
     */
    <T> boolean deleteUser(String key);

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
     * @param <T>
     * @return
     */
    <T> T getUserByKeyToken(String key, String token);

}
