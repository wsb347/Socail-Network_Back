package com.projectver2.spring.sns.user.web;

import com.projectver2.spring.sns.user.domain.User;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class AuthController {

    private final String NAVER_CLIENT_ID = "GuQtBxHc3QULrLswz1p2";
    private final String NAVER_REDIRECT_URI = "https://localhost:8080/login/oauth2/code/naver";
    private final String KAKAO_USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";
    private final String KAKAO_REDIRECT_URI = "http://localhost:8080/login/oauth2/code/kakao";
    private final String GOOGLE_USER_INFO_URL = "https://www.googleapis.com/oauth2/v3/userinfo";


    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    // 네이버 로그인 리다이렉트
    @GetMapping("/naver/login")
    public ResponseEntity<?> naverLogin() {
        String state = UUID.randomUUID().toString();
        String requestUrl = UriComponentsBuilder.fromHttpUrl("https://nid.naver.com/oauth2.0/authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", NAVER_CLIENT_ID)
                .queryParam("redirect_uri", NAVER_REDIRECT_URI)
                .queryParam("state", state)
                .toUriString();

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(requestUrl)).build();
    }

    @PostMapping("/naver/token")
    public ResponseEntity<?> requestAccessToken(@RequestParam String code, @RequestParam String state) {
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("grant_type", "authorization_code");
        parameters.add("client_id", NAVER_CLIENT_ID);
        parameters.add("client_secret", "dY2TEQRQ7i");
        parameters.add("code", code);
        parameters.add("state", state);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(parameters, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("https://nid.naver.com/oauth2.0/token", request, String.class);

        return ResponseEntity.ok(response.getBody());
    }

    @GetMapping("/naver/profile")
    public ResponseEntity<?> getNaverUserProfile(@RequestHeader("Authorization") String token) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange("https://openapi.naver.com/v1/nid/me", HttpMethod.GET, request, String.class);

        return ResponseEntity.ok(response.getBody());
    }

    // 구글 로그인 리다이렉트
    @GetMapping("/google/login")
    public String googleLogin() {
        return "redirect:/oauth2/authorization/google";
    }
    @PostMapping("/google/token")
    public ResponseEntity<?> requestGoogleAccessToken(@RequestParam String code) {
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("grant_type", "authorization_code");
        parameters.add("client_id", "658968810906-47m7o453ld9qqonmf6ljp7p7khgqhd7m.apps.googleusercontent.com");
        parameters.add("client_secret", "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");
        parameters.add("code", code);
        parameters.add("redirect_uri", "http://localhost:8080/login/oauth2/code/google");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(parameters, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("https://oauth2.googleapis.com/token", request, String.class);

        return ResponseEntity.ok(response.getBody());
    }

    @GetMapping("/google/profile")
    public ResponseEntity<?> getGoogleUserProfile(@RequestHeader("Authorization") String token) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(GOOGLE_USER_INFO_URL, HttpMethod.GET, request, String.class);

        if(response.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.ok(response.getBody());
        } else {
            throw new RuntimeException("Google login failed");
        }
    }

    @GetMapping("/kakao/login")
    public ResponseEntity<?> kakaoLogin() {
        String state = UUID.randomUUID().toString();
        String requestUrl = UriComponentsBuilder.fromHttpUrl("https://kauth.kakao.com/oauth/authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", "d00b4b1308a19f4775e3cddddbd4bca7")
                .queryParam("redirect_uri", KAKAO_REDIRECT_URI)
                .queryParam("state", state)
                .toUriString();

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(requestUrl)).build();
    }

    @PostMapping("/kakao/token")
    public ResponseEntity<?> requestKakaoAccessToken(@RequestParam String code, @RequestParam String state) {
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("grant_type", "authorization_code");
        parameters.add("client_id", "d00b4b1308a19f4775e3cddddbd4bca7");
        parameters.add("code", code);
        parameters.add("state", state);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(parameters, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("https://kauth.kakao.com/oauth/token", request, String.class);

        // 토큰 받아온 후 사용자 정보 가져오기
        JSONObject responseBody = new JSONObject(response.getBody());
        String accessToken = responseBody.getString("access_token");
        User user = getKakaoUserInfo(accessToken);


        return ResponseEntity.ok(response.getBody());
    }



    public User getKakaoUserInfo(String accessToken) {
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
            throw new RuntimeException("Kakao login failed");
        }
    }

}
