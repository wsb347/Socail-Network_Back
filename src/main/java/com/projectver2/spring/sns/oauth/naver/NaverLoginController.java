package com.projectver2.spring.sns.oauth.naver;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/naver")
public class NaverLoginController {

    private final String CLIENT_ID = "GuQtBxHc3QULrLswz1p2";
    private final String REDIRECT_URI = "https://localhost:8080/login/oauth2/code/naver";

    @GetMapping("/login")
    public ResponseEntity<?> requestNaverLogin() {

        String state = UUID.randomUUID().toString();

        String requestUrl = UriComponentsBuilder.fromHttpUrl("https://nid.naver.com/oauth2.0/authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", CLIENT_ID)
                .queryParam("redirect_uri", REDIRECT_URI)
                .queryParam("state", state)
                .toUriString();

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(requestUrl)).build();
    }

    @PostMapping("/token")
    public ResponseEntity<?> requestAccessToken(@RequestParam String code, @RequestParam String state) {
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("grant_type", "authorization_code");
        parameters.add("client_id", CLIENT_ID);
        parameters.add("client_secret", "dY2TEQRQ7i");
        parameters.add("code", code);
        parameters.add("state", state);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(parameters, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("https://nid.naver.com/oauth2.0/token", request, String.class);

        return ResponseEntity.ok(response.getBody());
    }
    @GetMapping("/profile")
    public ResponseEntity<?> getNaverUserProfile(@RequestHeader("Authorization") String token) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange("https://openapi.naver.com/v1/nid/me", HttpMethod.GET, request, String.class);

        return ResponseEntity.ok(response.getBody());
    }

}
