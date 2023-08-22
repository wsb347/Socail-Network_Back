package com.projectver2.spring.sns.oauth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OAuth2ExceptionHandler {

    @ExceptionHandler(value = OAuth2AuthenticationProcessingException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleOAuth2AuthenticationProcessingError(OAuth2AuthenticationProcessingException ex) {
        return ex.getMessage();
    }

}
