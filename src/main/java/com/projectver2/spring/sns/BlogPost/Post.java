package com.projectver2.spring.sns.BlogPost;

import com.projectver2.spring.sns.mypage.entity.Bookmark;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Post {
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

    @OneToMany(mappedBy = "post")
    private List<Bookmark> bookmarks;


    public void update(String content) {
        this.content = content;

    }
}