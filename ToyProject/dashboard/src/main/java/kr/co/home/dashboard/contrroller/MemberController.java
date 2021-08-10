package kr.co.home.dashboard.contrroller;

import kr.co.home.dashboard.domain.Address;
import kr.co.home.dashboard.dto.Res;
import kr.co.home.dashboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Embedded;
import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //TODO V2 Pageable을 활용하여 파라미터 처리
    @GetMapping
    public Res getMembersV1(@RequestParam("limit") int limit ,@RequestParam("offset") int offset ){
        List<Res.MemberDto> memberList =  memberService.findMembersInfo(limit, offset);
        return  Res.isOk(memberList);
    }

    //TODO V2 여러 사람을 한번에 등록 할 수 있게
    @PostMapping
    public Res createMemberV1(Res.MemberDto memberDto) {
        String message = memberService.createMember(memberDto);
        return Res.isOk(message);
    }

}
