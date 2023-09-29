package com.projectver2.spring.sns.BlogPost;

import com.projectver2.spring.sns.user.domain.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/post", produces = "application/json",
        method = {RequestMethod.GET, RequestMethod.POST})
public class PostController {
    private final PostService postService;

    @PostMapping(value = "/create")
    public String create(@AuthenticationPrincipal UserDetails userDetails, @RequestBody PostRequestDto requestDto) {
        if (userDetails != null) {
            String username = userDetails.getUsername();
            // 현재 로그인한 사용자 정보(username)를 사용하여 처리
            // requestDto.setUser(username);
            Post post = postService.save(requestDto);
            return "success";
        } else {
            // 로그인하지 않은 경우 처리
            return "error";
        }
    }

    @GetMapping()
    public List<Post> postAll(){
        return postService.postAll();
    }



//        @PutMapping("/update")
//        public String update(@PathVariable Integer id, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal SecurityUser principal) {
//            requestDto.setUser(principal.getUser());
//            return postService.update(id, requestDto);
//        }

}
