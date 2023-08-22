package com.projectver2.spring.sns.oauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class OAuth2Controller {
    @GetMapping("/loginSuccess")
    public String loginSuccess(){
        //TODO: Implement after succeeded login phase.
        return "Login successful";
    }

    @GetMapping("/loginFailure")
    public String loginFailure() {
        // TODO: Implement after fail login phase.
        return "Invalid id or password";
    }
}
