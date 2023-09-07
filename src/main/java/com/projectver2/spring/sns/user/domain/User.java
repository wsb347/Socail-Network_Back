package com.projectver2.spring.sns.user.domain;

import com.projectver2.spring.sns.mypage.entity.Bookmark;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

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

    @OneToMany(mappedBy = "user")
    private List<Bookmark> bookmarks;

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

// User.java의 추가 부분

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

