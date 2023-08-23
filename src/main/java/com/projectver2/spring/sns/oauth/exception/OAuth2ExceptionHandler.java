package com.projectver2.spring.sns.oauth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestControllerAdvice
public class OAuth2ExceptionHandler {

    @ExceptionHandler(value = OAuth2AuthenticationException.class)
public ModelAndView handleOAuth2AuthenticationError(OAuth2AuthenticationException ex) {
    // 사용자가 로그인을 취소한 경우
    if (ex.getError().getErrorCode().equals("access_denied")) {
        ModelAndView mav = new ModelAndView("redirect:/login");
        return mav;
    }
    ModelAndView mav = new ModelAndView();
    mav.addObject("message", "OAuth2 인증 오류가 발생했습니다.");
    mav.setViewName("error");
    return mav;
}
}
