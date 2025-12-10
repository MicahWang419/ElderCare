package com.eldercare.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "care_posts")
public class CarePost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // ===== 关系字段 =====
    private Integer clientId;
    private Integer providerId;

    // ===== 基本信息 =====
    private String title;

    @Column(length = 2000)
    private String description;

    // ===== 职责 & 要求 =====
    @Column(length = 2000)
    private String responsibilities;

    @Column(length = 2000)
    private String requirements;

    // ===== 工作信息 =====
    private String location;

    // 工资（例如：时薪）
    private Integer salary;

    // 工作类型 / Job Type
    // 例如："Care", "Nursing", "Companion"
    private String typeName;

    // ===== 系统字段 =====
    private boolean active = true;

    private LocalDateTime createdAt = LocalDateTime.now();

    // ===== 构造方法 =====
    public CarePost() {}

    // ===== GETTERS & SETTERS =====

    public Integer getId() {
        return id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
