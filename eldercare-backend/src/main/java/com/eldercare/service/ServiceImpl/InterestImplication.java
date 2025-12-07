package com.eldercare.service.ServiceImpl;

import com.eldercare.model.Interest;
import com.eldercare.repository.InterestRepository;
import com.eldercare.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterestImplication implements InterestService {

    @Autowired
    private InterestRepository interestRepository;

    @Override
    public void addInterest(Integer userId, Integer postId) {

        Optional<Interest> optional =
                interestRepository.findByUserIdAndPostId(userId, postId);

        if (optional.isPresent()) {
            Interest interest = optional.get();

            // 已存在，但之前被软删除 -> 恢复
            if (interest.getIsDeleted() == 1) {
                interest.setIsDeleted(0);
                interestRepository.save(interest);
            }
            // 已存在且未删除：什么都不做
        } else {
            // 从未存在过：新建
            Interest interest = new Interest();
            interest.setUserId(userId);
            interest.setPostId(postId);
            interest.setIsDeleted(0);
            interestRepository.save(interest);
        }
    }

    @Override
    public void removeInterest(Integer userId, Integer postId) {
        interestRepository.findByUserIdAndPostId(userId, postId)
                .ifPresent(interest -> {
                    interest.setIsDeleted(1);
                    interestRepository.save(interest);
                });
    }

    @Override
    public List<Interest> getUserInterests(Integer userId) {
        return interestRepository.findByUserIdAndIsDeleted(userId, 0);
    }

    @Override
    public boolean isInterested(Integer userId, Integer postId) {
        return interestRepository
                .existsByUserIdAndPostIdAndIsDeleted(userId, postId, 0);
    }


}
