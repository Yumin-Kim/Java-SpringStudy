package springcore.fastcamp.fastcamp.security.kakao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Getter
public class KakaoUserInfo {
    Long id;
    String email;
    String nickname;


}

