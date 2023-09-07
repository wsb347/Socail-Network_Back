package com.projectver2.spring.sns.BlogPost;

import com.projectver2.spring.sns.user.domain.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    // create
    @Transactional
    public Post save(PostRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity());
    }

    public List<Post> postAll() {
       return postRepository.findAllByOrderByIdDesc();
    }

//    public String update(Integer id, PostRequestDto requestDto) {
//        String message = "fail";
//        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
//
//        // 작성자만 수정 가능
//        if (postRepository.findById(id).get().getUser().getId().equals(requestDto.getUser().getId())) {
//            post.update(requestDto.getContent());
//            message = "success";
//        }
//
//        return message;
//
//        public String delete(Integer id, User user) {
//            String message = "fail";
//            Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
//
//            if (post.getUser()().getId().equals(user.getId())) { // 작성자만 삭제 가능
//                postRepository.delete(post);
//                message = "success";
//            }
//
//            return message;
//
//    }
}
