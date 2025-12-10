package com.eldercare.dto;

import java.util.List;

public class CreatePostRequest {
    public Integer id;
    public Integer clientId;
    public String title;
    public String description;
    public String location;
    public String responsibilities;
    public String requirements;

    // type / tag
    public String typeName;
    public List<String> tags;

    // ✅ 新增：工资
    public Integer salary;



}
