package com.eldercare.repository;

import com.eldercare.model.CarePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarePostRepository extends JpaRepository<CarePost, Integer> {
    List<CarePost> findByClientId(Integer clientId);
    List<CarePost> findByActiveTrue();

    @Query(value = """
        SELECT * FROM care_posts
        WHERE active = 1
          AND (:keyword IS NULL 
               OR LOWER(title) LIKE LOWER(CONCAT('%', :keyword, '%')))
        """, nativeQuery = true)
    List<CarePost> searchByTitle(@Param("keyword") String keyword);


    @Query("""
           SELECT p
           FROM CarePost p
           WHERE p.active = true
             AND (:location IS NULL OR p.location = :location)
             AND (:typeName IS NULL OR p.typeName = :typeName)
             AND (:minSalary IS NULL OR p.salary >= :minSalary)
             AND (:maxSalary IS NULL OR p.salary <= :maxSalary)
           """)
    List<CarePost> filterPosts(
            @Param("location") String location,
            @Param("typeName") String typeName,
            @Param("minSalary") Integer minSalary,
            @Param("maxSalary") Integer maxSalary
    );
}
