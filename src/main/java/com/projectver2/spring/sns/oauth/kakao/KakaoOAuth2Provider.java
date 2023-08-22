package com.projectver2.spring.sns.oauth.kakao;

import com.projectver2.spring.sns.user.domain.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

@Service
public class KakaoOAuth2Provider {

    private static final String KAKAO_AUTHORIZATION_URL = "https://kauth.kakao.com/oauth/authorize";
    private static final String KAKAO_TOKEN_URL = "https://kauth.kakao.com/oauth/token";
    private static final String KAKAO_USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";

    public User getUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        ResponseEntity<String> response = restTemplate.exchange(KAKAO_USER_INFO_URL, HttpMethod.GET, new HttpEntity<String>(headers), String.class);

        if(response.getStatusCode() == HttpStatus.OK) {
            JSONObject userInfo = new JSONObject(response.getBody());

            Long id = userInfo.getLong("id");
            String email = userInfo.getJSONObject("kakao_account").getString("email");
            String nickname = userInfo.getJSONObject("kakao_account").getString("profile_nickname");

            User user = new User();
            user.setKakaoId(id);
            user.setEmail(email);
            user.setNickname(nickname);

            return user;
        } else {
            throw new RuntimeException("카카오 로그인 중 문제가 발생했습니다.");
        }
    }
}
