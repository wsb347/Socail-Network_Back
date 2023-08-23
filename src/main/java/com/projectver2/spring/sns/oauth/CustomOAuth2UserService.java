package com.projectver2.spring.sns.oauth;

import com.projectver2.spring.sns.user.domain.User;
import com.projectver2.spring.sns.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserService userService;

    @Override
public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(userRequest);

    Long kakaoId = (Long) oAuth2User.getAttribute("id");
    Map<String, Object> properties = oAuth2User.getAttribute("properties");
    String nickname = (String) Objects.requireNonNull(properties).get("profile_nickname");
    Map<String, Object> kakaoAccount = oAuth2User.getAttribute("kakao_account");
    String email = (String) Objects.requireNonNull(kakaoAccount).get("email");

    // DB에서 이메일로 사용자를 찾습니다.
    Optional<User> existingUser = userService.findByUserid(email);

    User user;
    if (existingUser.isPresent()) {
        // 이미 DB에 있는 사용자라면 해당 정보를 갱신
        user = existingUser.get();
        user.setNickname(nickname); // 기타 필요한 정보 갱신
    } else {
        // DB에 없는 새로운 사용자라면 새로운 User 객체를 생성하고 저장
        user = new User();
        user.setKakaoId(kakaoId);
        user.setNickname(nickname);
        user.setEmail(email);
        user.setLoginType("SOCIAL"); // 소셜 로그인이므로 DB에 로그인타입 SOCIAL로 명시
    }

    userService.saveOrUpdate(user);

    return new DefaultOAuth2User(
            Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
            oAuth2User.getAttributes(),
            "email");
}
}


