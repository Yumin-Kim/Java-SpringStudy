package springcore.fastcamp.fastcamp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springcore.fastcamp.fastcamp.domin.ProductUser;
import springcore.fastcamp.fastcamp.domin.UserRole;
import springcore.fastcamp.fastcamp.dto.SignupRequestDto;
import springcore.fastcamp.fastcamp.repository.ProductUserRepository;
import springcore.fastcamp.fastcamp.security.kakao.KakaoOAuth2;
import springcore.fastcamp.fastcamp.security.kakao.KakaoUserInfo;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class userService {
    private final ProductUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final KakaoOAuth2 kakaoOAuth2;
    private final AuthenticationManager authenticationManager;

    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    public void registerUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();

        Optional<ProductUser> findUserInfo = userRepository.findByname(username);

        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();
        UserRole userRole = UserRole.USER;
        if (requestDto.isAdmin()) {
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new RuntimeException("관리자 권한 암호 오류");
            }
            userRole = UserRole.ADMIN;
        }
        ProductUser productUser = new ProductUser(username, password, email,userRole);
        userRepository.save(productUser);
    }

    public void kakaoLogin(String code) {
        KakaoUserInfo userInfo = kakaoOAuth2.getUserInfo(code);
        Long kakaoId = userInfo.getId();
        String nickname = userInfo.getNickname();
        String email = userInfo.getEmail();

        String username = nickname;
        String password = kakaoId + ADMIN_TOKEN;

        ProductUser kakaoUser = userRepository.findByKakaoId(kakaoId).orElse(null);

        if (kakaoUser == null) {
            String encodePwd = passwordEncoder.encode(password);
            UserRole userRolo = UserRole.USER;
            kakaoUser = new ProductUser(nickname, encodePwd, email, userRolo, kakaoId);
//            userRepository.save(kakaoUser);
        }

        Authentication kakaoUserPassword = new UsernamePasswordAuthenticationToken(username, password);

        Authentication authenticate = authenticationManager.authenticate(kakaoUserPassword);

        SecurityContextHolder.getContext().setAuthentication(authenticate);

    }
}
