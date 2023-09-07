package com.projectver2.spring.sns.user.web;

import com.projectver2.spring.sns.user.UserSignupDto;
import com.projectver2.spring.sns.user.domain.User;
import com.projectver2.spring.sns.user.repository.UserRepository;
import com.projectver2.spring.sns.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        User createdUser = userService.saveOrUpdate(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@ModelAttribute UserSignupDto userSignupDto) {
        // 이미 존재하는 아이디인지 확인
        Optional<User> existingUser = userService.findByUserid(userSignupDto.getUserid());
        if (existingUser.isPresent()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "이미 존재하는 아이디입니다.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        // login_type을 NORMAL로 설정
        userSignupDto.setLoginType("NORMAL");
        User registeredUser = userService.saveOrUpdate(userSignupDto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @GetMapping("/signup")
    public String signup() {

        return "signup";
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String userid, @RequestParam String password, HttpSession session) {
        Optional<User> loginUser = userService.findByUserid(userid);

        if (loginUser.isPresent()) {
            User user = loginUser.get();
            if (user.getPassword().equals(password)) {
                session.setAttribute("loginUser", user);
                Map<String, String> response = new HashMap<>();
                response.put("message", "로그인에 성공하였습니다.");
                response.put("redirect", "/main");
                return ResponseEntity.ok(response);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디 또는 비밀번호가 잘못되었습니다.");
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginUser");
        return "redirect:/login";
    }

    @GetMapping("/mypage/{userid}")
    public String findByUserid(@PathVariable String userid, Model model) {
        Optional<User> user = userService.findByUserid(userid);
        if (user.isPresent()) {
            ResponseEntity.ok(user.get());
            model.addAttribute("user", user);
            return "mypage";
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("error", "해당 유저를 찾을 수 없습니다.");
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            return "redirect:/users/signup";
       }
    }


    @PutMapping("/mypage/{userid}")
    public ResponseEntity<?> userModify(@PathVariable String userid, @RequestBody User user, HttpSession session) {

        Optional<User> current_login_user  = userService.findByUserid(userid);
        if (current_login_user .isPresent()) {
            User createdUser = userService.userModify(userid,user);

            return ResponseEntity.ok(current_login_user .get());
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("error", "해당 유저를 찾을 수 없습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

    }

    @DeleteMapping("/{userid}")
    public String deleteUser(@PathVariable String userid) {
        Optional<User> current_login_user  = userService.findByUserid(userid);
        if (current_login_user .isPresent()) {
            userService.userDelete(userid);

            ResponseEntity.ok("회원탈퇴가 완료되었습니다.");
            return "redirect:/users/signup";
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("error", "해당 유저를 찾을 수 없습니다.");
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            return "redirect:/users/signup";
        }
    }

}


