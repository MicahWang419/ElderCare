package com.eldercare.controller;

import com.eldercare.model.Interest;
import com.eldercare.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interests")
@CrossOrigin("*")
public class InterestController {

    @Autowired
    private InterestService interestService;

    /** 添加感兴趣 */
    @PostMapping
    public ResponseEntity<?> addInterest(
            @RequestParam Integer userId,
            @RequestParam Integer postId) {

        interestService.addInterest(userId, postId);
        return ResponseEntity.ok("Interested");
    }

    /** 取消感兴趣 */
    @DeleteMapping
    public ResponseEntity<?> removeInterest(
            @RequestParam Integer userId,
            @RequestParam Integer postId) {

        interestService.removeInterest(userId, postId);
        return ResponseEntity.ok("Interest removed");
    }

    /** 查询用户是否感兴趣 */
    @GetMapping("/check")
    public boolean checkInterest(
            @RequestParam Integer userId,
            @RequestParam Integer postId) {

        return interestService.isInterested(userId, postId);
    }

    @GetMapping("/user/{userId}")
    public List<Interest> getUserInterests(@PathVariable Integer userId) {
        return interestService.getUserInterests(userId);
    }
}

