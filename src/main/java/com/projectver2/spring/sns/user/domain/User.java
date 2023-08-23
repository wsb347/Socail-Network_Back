package com.projectver2.spring.sns.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    private String username;
    @Getter
    private String userid;
    @Getter
    private String password;
    @Getter
    private String city;
    @Getter
    private String email;
    private String loginType;
    private Long kakaoId;
    private String nickname;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setKakaoId(Long kakaoId) {
        this.kakaoId = kakaoId;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public void setLoginType(String loginType) {
    this.loginType = loginType;
}

}

