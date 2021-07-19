package springcore.fastcamp.fastcamp.security.kakao;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class KakaoOAuth2 {
    public KakaoUserInfo getUserInfo(String code) {
        String accessToken = getAccessToken(code);
        KakaoUserInfo userInfo = getUserInfoByToken(accessToken);
        return userInfo;
    }

    private String getAccessToken(String code) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("grant_type", "authorization_code");
        params.add("client_id","bd64ffb97f170defc689949aaa3dedfa");
        params.add("redirect_uri","http://localhost:8080/user/kakao/callback");
        params.add("code",code);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        String tokenJSON = response.getBody();
        JSONObject jsonObject = new JSONObject(tokenJSON);
        String accessToken = jsonObject.getString("access_token");
        return accessToken;
    }

    private KakaoUserInfo getUserInfoByToken(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        JSONObject body = new JSONObject(response.getBody());
        Long id = body.getLong("id");
        String email = body.getJSONObject("kakao_account").getString("email");
        String nickname = body.getJSONObject("properties").getString("nickname");

        return new KakaoUserInfo(id, email, nickname);
    }
}
