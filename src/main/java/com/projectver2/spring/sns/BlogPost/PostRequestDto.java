package com.projectver2.spring.sns.BlogPost;

import com.projectver2.spring.sns.user.domain.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class PostRequestDto {

    private String content;
    private User user;
    private String title;
    private String image;
    private int like;
    private Date regDate;

    @Builder
    public PostRequestDto(String content, User user) {
        this.content = content;
        this.user = user;
    }
    public Post toEntity() {
        return Post.builder()
                .content(content)
                .user(toEntity().getUser())
                .title(title)
                .image(image)
                .like(like)
                .regDate(LocalDateTime.now())
                .build();
    }
}