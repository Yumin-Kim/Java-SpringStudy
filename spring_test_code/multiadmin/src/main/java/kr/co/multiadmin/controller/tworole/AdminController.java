package kr.co.multiadmin.controller.tworole;

import kr.co.multiadmin.domain.Admin;
import kr.co.multiadmin.repository.AdminRepository;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@RequestMapping("/v1/admin")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final AdminRepository adminRepository;

    @PostMapping
    public String createAdmin(@RequestBody AdminDTO adminDTO, HttpServletRequest request) {
        log.info("test = {}{} " , adminDTO.getName(),adminDTO.getUsername());
        Admin admin = adminDTO.toEntity(adminDTO);
        adminRepository.save(admin);
        return "admin";
    }

    @ExceptionHandler({RuntimeException.class})
    public String runtimeException(Exception e ) {
        return e.getMessage();
    }

    @GetMapping("/login")
    public AdminDAO loginAdmin(@RequestParam("username") String username, HttpServletRequest request) {
        Admin admin = adminRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("존쟈하지 않는 계정입니다."));
        HttpSession session = request.getSession();
        log.info("session = {}",session.getAttribute("admin"));
        if (session.getAttribute("admin") == null) {
            session.setAttribute("admin",admin.getId());
            Object admin1 = session.getAttribute("admin");
            System.out.println("admin1.getClass() = " + admin1);
        }

        return new AdminDAO("success");
    }

    @GetMapping("/logout")
    public AdminDAO logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("admin");
        return new AdminDAO("로그아웃 성공");
    }

    @GetMapping("/list")
    public AdminDTO getAdminList(HttpServletRequest request) throws RuntimeException {
        HttpSession session = request.getSession();
        log.info("sessionInfo = {}", session.getAttribute("admin"));
        Long admin = (Long)Optional.ofNullable(session.getAttribute("admin"))
                .orElseThrow(() -> new RuntimeException("세션 정보를 확인해주세요"));
        Admin findByAdmin = adminRepository.findById(admin).orElseThrow(() -> new RuntimeException("존재하지 않는 아이디 입니다"));
        AdminDTO build = new AdminDTO(findByAdmin.getUsername(), findByAdmin.getName());
        System.out.println("build = " + build);
        return build;
    }

    @Getter
    @Setter
    @Builder
    @ToString
    private static class AdminDTO{
        private String username;
        private String name;
        public Admin toEntity(AdminDTO adminDTO) {
            return Admin.builder()
                    .name(adminDTO.name)
                    .username(adminDTO.username)
                    .build();
        }

    }

    @Setter
    @Getter
    @AllArgsConstructor
    private static class AdminDAO {
        private String message;
    }
}
