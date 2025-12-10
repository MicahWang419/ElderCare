package com.eldercare.model;

import jakarta.persistence.*;

@Entity
@Table(name = "type")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "post_id", nullable = false)
    private Integer postId;

    @Column(name = "type_name", nullable = false)
    private String typeName;

    @Column(name = "tag_name", nullable = false)
    private String tagName;

    // ===== GETTERS & SETTERS =====

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getPostId() { return postId; }
    public void setPostId(Integer postId) { this.postId = postId; }

    public String getTypeName() { return typeName; }
    public void setTypeName(String typeName) { this.typeName = typeName; }

    public String getTagName() { return tagName; }
    public void setTagName(String tagName) { this.tagName = tagName; }
}
