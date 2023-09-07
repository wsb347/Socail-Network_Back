package com.projectver2.spring.sns.mypage.repository;

import com.projectver2.spring.sns.BlogPost.Post;
import com.projectver2.spring.sns.mypage.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostListRepository extends JpaRepository<Post, Integer> {

     List<Post> findPostByUser(String user);


}
