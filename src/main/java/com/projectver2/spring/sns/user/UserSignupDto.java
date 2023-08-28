package com.projectver2.spring.sns.user;

import com.projectver2.spring.sns.user.domain.User;
import lombok.Getter;

public class UserSignupDto {
    @Getter
    private String username;
    private String userid;
    private String password;
    private String city;
    private String email;
    private String loginType = "NORMAL";

    public UserSignupDto() {}

    public String getUserid() {
        return userid;
    }

    public String getPassword() {
        return password;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User toEntity() {
        User user = new User();
        user.setUsername(this.username);
        user.setUserid(this.userid);
        user.setPassword(this.password);
        user.setCity(this.city);
        user.setEmail(this.email);
        user.setLoginType(this.loginType);
        return user;
    }

    public void setLoginType(String loginType) {
    this.loginType = loginType;
}

public String getLoginType() {
    return loginType;
}
}
