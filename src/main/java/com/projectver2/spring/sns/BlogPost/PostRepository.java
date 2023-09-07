package com.projectver2.spring.sns.BlogPost;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    public List<Post> findAllByOrderByIdDesc();

    public List<Post> findPostByUser(String user);


}