package startspring.hello_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import startspring.hello_spring.domain.Member;
import startspring.hello_spring.service.UserService;

import java.util.List;

@Controller
public class MemberController {
    private final UserService userService;
    //Field Innjection
//    @Autowired
//    private final UserService userServiceFieldInjection;
//    private UserService userService;
//    setter Innjection
//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }


    /**
     * 스프링 컨테이너로 인해서 스프링 빈이 관리되어진다.
     * 현재 빈에 대한 이해
     * 예를 들어 컨테이너가 빈을 관리 하지 않게 되면 서버 요청이 진행 될때마다 Controller 객체는 요청에 따라 무한히 생성되고 삭제가 된다고 한다.
     * 이런 생산과 삭제가 반복되면 서버에 부하가 오게되고 곧 다운이 되게 되는데 이런 문제를 핵결하기 위해 스프링 컨테이너에서 어노테이션을 통해 인스턴스화되고 관리할 수 있는 싱글톤으로  스프링 빈이 등록
     * 또한 new로 Controller등을 선언하면 빈이되지
     * AutoWired 어노테이션을 통해서 컨테이너로 DI를 할 수 있게 해준다.
     * DI에는 필드 주입 ,  setter 주입 , 생성자 주입 존재
     * 의존 관계가 실행중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입
     * 스프링 빈을 등록하는 방법
     * 1. 컴포넌트 스캔과 자동 의존관계 설정
     * 2. 자바 코드로 직접 스프링 빈 등록
     *
     * @param userService
     */
    @Autowired
    public MemberController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm memberForm) {
        Member member = new Member();
        member.setName(memberForm.getName());
        System.out.println(memberForm.getName());
        userService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = userService.findMember();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
