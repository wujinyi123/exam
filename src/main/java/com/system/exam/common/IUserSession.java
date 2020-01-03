package com.system.exam.common;

/**
 * UserSession工具
 */
public interface IUserSession {
    /**
     * 生成uuid(token)，并把用户存入Redis
     * 默认项目名字token
     * @param obj
     * @param <T>
     * @return
     */
    <T> String saveUser(T obj);

    /**
     * 生成uuid(token)，并把用户存入Redis
     * @param obj
     * @param projectName
     * @param <T>
     * @return
     */
    <T> String saveUser(T obj, String projectName);

    /**
     * 生成uuid(token)，并把用户存入Redis
     * 默认项目名字token
     * @param obj
     * @param time
     * @param <T>
     * @return
     */
    <T> String saveUser(T obj, int time);

    /**
     * 生成uuid(token)，并把用户存入Redis
     * @param obj
     * @param projectName
     * @param time
     * @param <T>
     * @return
     */
    <T> String saveUser(T obj, String projectName, int time);

    /**
     * 获取当前用户
     * 默认项目名字token
     * @param <T>
     * @return
     */
    <T> T getUser();

    /**
     * 获取当前用户
     * @param projectName
     * @param <T>
     * @return
     */
    <T> T getUser(String projectName);

    /**
     * 删除uuid(token)对应的对象
     * 默认项目名字token
     * @param <T>
     * @return
     */
    <T> boolean deleteUser();

    /**
     * 删除uuid(token)对应的对象
     * @param projectName
     * @param <T>
     * @return
     */
    <T> boolean deleteUser(String projectName);

    /**
     * 从cookie中获取uuid(token)
     * 默认项目名字token
     * @return
     */
    String getUserToken();

    /**
     * 从cookie中获取uuid(token)
     * @param projectName
     * @return
     */
    String getUserToken(String projectName);

}
