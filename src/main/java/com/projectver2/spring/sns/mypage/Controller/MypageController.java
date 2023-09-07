package com.projectver2.spring.sns.mypage.Controller;

import com.projectver2.spring.sns.BlogPost.Post;
import com.projectver2.spring.sns.mypage.entity.Bookmark;
import com.projectver2.spring.sns.mypage.service.PostListService;
import com.projectver2.spring.sns.user.domain.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mypage")
public class MypageController {
    @Autowired
    private PostListService postListService;

    @GetMapping("/posts/{username}")
    public List<Post> postListByMyPost(@PathVariable String username, User user, HttpSession session){
       // String username = (String) session.getAttribute(user.getUsername());
        return postListService.postListByMyPost(username);
    }

//    @GetMapping("/bookmark/{username}")
//    public List<Bookmark> postListByBookmark(@PathVariable String username, Post post, HttpSession session){
//        User user = new User();
//
//       // String username = (String) session.getAttribute(user.getUsername());
//        int like = post.getLike();
//
//        return postListService.postListByBookmark(username, like);
//    }




}
