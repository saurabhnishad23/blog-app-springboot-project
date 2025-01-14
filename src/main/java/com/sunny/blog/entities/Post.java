package com.sunny.blog.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
//@Table(name = "posts")
@Table(name = "posts", indexes = @Index(name = "idx_post_id", columnList = "postId"))
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int postId;

    @Column(name = "post_title", length = 200, nullable = false)
    private String postTitle;

    @Column(length = 10000)
    private String content;
    @Column(length = 1000)
    private String imageName;

    private Date addedDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

}
