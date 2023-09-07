package com.projectver2.spring.sns.mypage.service;

import com.projectver2.spring.sns.BlogPost.Post;
import com.projectver2.spring.sns.mypage.entity.Bookmark;
import com.projectver2.spring.sns.mypage.repository.PostListRepository;
import com.projectver2.spring.sns.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostListService {

    private final PostListRepository postListRepository;

    public List<Post> postListByMyPost(String username) {
        return postListRepository.findPostByUser(username);
    }

//    public List<Post> postListByBookmark(String username, int like) {
//
//        return postListRepository.findPostByUserAndLike(username, like);
//    }

}
