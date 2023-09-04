package com.projectver2.spring.sns.BlogPost;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Integer id;

    //@ManyToOne
    //@JoinColumn(name="user", nullable = false, updatable = false)
    private String user;

    @Column(columnDefinition = "TEXT")
    private String content;
    private String title;
    private String image;
    private int like;
    private LocalDateTime regDate;

    public void update(String content) {
        this.content = content;

    }
}

