package kr.co.home.dashboard.api;

import kr.co.home.dashboard.dto.MemberForm;
import kr.co.home.dashboard.dto.Res;
import kr.co.home.dashboard.exception.MemberNotFoundException;
import kr.co.home.dashboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class MemberAPIController {

    private final MemberService memberService;

    //TODO V2 Pageable을 활용하여 파라미터 처리
    @GetMapping
    public Res getMembersV1(@RequestParam("limit") int limit, @RequestParam("offset") int offset) {
        List<Res.MemberDto> memberList = memberService.findMembersInfo(limit, offset);
        return Res.isOk(memberList);
    }

    //TODO V2 여러 사람을 한번에 등록 할 수 있게
    @PostMapping
    public Res createMemberV1(@Validated({MemberForm.MemberCreateForm.class}) MemberForm memberForm) {
        String message = memberService.createMember(memberForm);
        return Res.isOk(message);
    }

    //Form 정보를 통해 Update 쿼리 발생
    @PutMapping("/{userId}")
    public Res updateMember(@RequestBody @Validated({MemberForm.MemberUdpateForm.class}) MemberForm memberForm, @PathVariable("userId") Long userId) {
        Res.MemberDto member = memberService.updateMember(userId, memberForm);
        return Res.isOk(member);
    }

    //실질적으로 데이터 삭제를 하지않고 is_deleted 컬럼을 활용하여 진행 중이다.
    @DeleteMapping("/delete/{userId}")
    public Res deletedMember(@PathVariable("userId") Long userId) {
        Res.MemberDto member = memberService.deleteMember(userId);
        return Res.isOk(member);
    }

}
