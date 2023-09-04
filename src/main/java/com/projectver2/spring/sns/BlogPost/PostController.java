package com.projectver2.spring.sns.BlogPost;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/post", produces = "application/json",
        method = {RequestMethod.GET, RequestMethod.POST})

public class PostController {
    private final PostService postService;

    @PostMapping(value = "/create")
    public String create(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal SecurityUser principal) {
        // 프론트에서 받은 토큰으로 Member 구분함
        requestDto.setUser(principal.getUser()); // 메서드명 그대로 사용
        Post post = postService.save(requestDto);

        return "success";
    }

}
