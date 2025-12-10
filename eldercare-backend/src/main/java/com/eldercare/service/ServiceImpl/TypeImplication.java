package com.eldercare.service.ServiceImpl;

import com.eldercare.model.CarePost;
import com.eldercare.repository.TypeRepository;
import com.eldercare.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeImplication implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public List<CarePost> getPostsByTag(String tagName) {
        return typeRepository.findPostsByTagName(tagName);
    }
}
