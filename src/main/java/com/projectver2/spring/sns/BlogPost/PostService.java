package com.projectver2.spring.sns.BlogPost;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    // create
    @Transactional
    public Post save(PostRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity());
    }
}
