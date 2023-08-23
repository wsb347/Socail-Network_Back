package com.projectver2.spring.sns.oauth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController

public class OAuth2Controller {
    @GetMapping("/login/success")
    public ResponseEntity<?> loginSuccess(){
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "로그인 성공!");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/login/failure")
    public ResponseEntity<?> loginFailure() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "failure");
        response.put("message", "로그인 실패. 아이디나 비밀번호를 확인해주세요.");
        return ResponseEntity.badRequest().body(response);
    }
}
