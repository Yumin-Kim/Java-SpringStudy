package springcore.fastcamp.fastcamp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springcore.fastcamp.fastcamp.domin.ProductUser;
import springcore.fastcamp.fastcamp.domin.UserRole;
import springcore.fastcamp.fastcamp.dto.SignupRequestDto;
import springcore.fastcamp.fastcamp.repository.ProductUserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSerive {
    private final ProductUserRepository userRepository;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    public void registerUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        Optional<ProductUser> findUserInfo = userRepository.findByname(username);
//        findUserInfo.orElseThrow(IllegalArgumentException::new);
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
}
