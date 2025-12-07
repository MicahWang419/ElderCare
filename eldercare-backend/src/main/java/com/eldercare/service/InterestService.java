package com.eldercare.service;

import com.eldercare.model.Interest;

import java.util.List;

public interface InterestService {

    /** 添加感兴趣 */
    void addInterest(Integer userId, Integer postId);

    /** 取消感兴趣（软删除） */
    void removeInterest(Integer userId, Integer postId);

    /** 查询用户所有感兴趣的记录 */
    List<Interest> getUserInterests(Integer userId);

    /** 判断用户是否已对某个 post 感兴趣 */
    boolean isInterested(Integer userId, Integer postId);

}

