package com.projectver2.spring.sns.mypage.repository;

import com.projectver2.spring.sns.BlogPost.Post;
import com.projectver2.spring.sns.mypage.entity.Bookmark;
import com.projectver2.spring.sns.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostListRepository extends JpaRepository<Post, Integer> {

     List<Post> findPostByUser(String user);

    /* @Query("SELECT p FROM post p inner JOIN user u ON p.user = u.userid WHERE u.userid = :userid")
     List<Post> findPostByUserandlike(@Param("userid") User user, Post post);*/
}
