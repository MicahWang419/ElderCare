package com.eldercare.repository;

import com.eldercare.model.Type;
import com.eldercare.model.CarePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {

    // 如果以后要查 Type 本身可以用
    List<Type> findByTagName(String tagName);

    /**
     * 根据 tagName 直接查对应的 CarePost 列表
     */
    @Query("""
           SELECT p
           FROM CarePost p
           JOIN Type t ON p.id = t.postId
           WHERE t.tagName = :tagName
           """)
    List<CarePost> findPostsByTagName(@Param("tagName") String tagName);
}
