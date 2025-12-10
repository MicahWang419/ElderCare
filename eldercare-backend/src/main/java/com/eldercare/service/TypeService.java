package com.eldercare.service;

import com.eldercare.model.CarePost;

import java.util.List;

public interface TypeService {

    /**
     * 根据 tag 名称，返回所有关联的 CarePost
     */
    List<CarePost> getPostsByTag(String tagName);
}
