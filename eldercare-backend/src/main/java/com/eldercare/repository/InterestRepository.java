package com.eldercare.repository;

import com.eldercare.model.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Integer> {

    /**
     * 根据 userId 和 postId 查找一条 interest 记录
     */
    Optional<Interest> findByUserIdAndPostId(Integer userId, Integer postId);

    /**
     * 查询某个用户所有未被删除的 interest
     */
    List<Interest> findByUserIdAndIsDeleted(Integer userId, Integer isDeleted);

    /**
     * 判断用户是否已经对某个 post 感兴趣（且未删除）
     */
    boolean existsByUserIdAndPostIdAndIsDeleted(
            Integer userId,
            Integer postId,
            Integer isDeleted
    );
}

