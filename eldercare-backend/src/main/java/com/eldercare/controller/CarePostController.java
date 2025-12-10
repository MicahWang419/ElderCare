package com.eldercare.controller;

import com.eldercare.dto.CreatePostRequest;
import com.eldercare.model.CarePost;
import com.eldercare.model.Type;
import com.eldercare.repository.CarePostRepository;
import com.eldercare.repository.TypeRepository;
import com.eldercare.service.CarePostService;
import com.eldercare.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin("*")
public class CarePostController {

    @Autowired
    private CarePostRepository carePostRepository;
    @Autowired
    private CarePostService carePostService;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired(required = false)
    private TypeService typeService;

    @PostMapping
    public CarePost createPost(@RequestBody CreatePostRequest req) {
        CarePost post = new CarePost();

        post.setClientId(req.clientId);
        post.setTitle(req.title);
        post.setDescription(req.description);
        post.setLocation(req.location);

        post.setResponsibilities(req.responsibilities);
        post.setRequirements(req.requirements);

        post.setSalary(req.salary);
        post.setTypeName(req.typeName);

        CarePost saved = carePostRepository.save(post);

        // 保存 tags 到 type 表
        if (req.tags != null && !req.tags.isEmpty()) {
            for (String tag : req.tags) {
                Type t = new Type();
                t.setPostId(saved.getId());
                t.setTypeName(req.typeName); // 可用 saved.getTypeName()
                t.setTagName(tag);
                typeRepository.save(t);
            }
        }

        return saved;
    }

    @GetMapping
    public List<CarePost> getAllPosts() {
        return carePostRepository.findAll();
    }

    @GetMapping("/search")
    public List<CarePost> getPostsByTitle(@RequestParam String title) {
        return carePostService.SearchByTitle(title);
    }

    @GetMapping("/{id}")
    public Optional<CarePost> getPostById(@PathVariable Integer id) {
        return carePostService.getDetailById(id); //获取具体的post信息
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePostById(@PathVariable Integer id) { //软删除
        boolean success = carePostService.softDeleted(id);
        if (success) {
            return ResponseEntity.ok("Post deleted.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-tag")
    public List<CarePost> getPostsByTag(@RequestParam String tag) {
        // 如果你有 TypeService 的实现，可以用 typeService.getPostsByTag(tag)
        // 这里直接用 Repository 的 JPQL 方法也可以
        if (typeService != null) {
            return typeService.getPostsByTag(tag);
        }
        // 兜底：如果暂时没写 TypeService，就用 TypeRepository 的查询
        // 记得在 TypeRepository 里实现 findPostsByTagName
        throw new UnsupportedOperationException("TypeService or TypeRepository query not implemented yet.");
    }

    @GetMapping("/filter")
    public List<CarePost> filterPosts(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String typeName,
            @RequestParam(required = false) Integer minSalary,
            @RequestParam(required = false) Integer maxSalary
    ) {
        return carePostRepository.filterPosts(location, typeName, minSalary, maxSalary);
    }

}
