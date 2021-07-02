package jpacore.jpashop.api;

import jpacore.jpashop.domain.Member;
import jpacore.jpashop.dto.CreateMemberReponse;
import jpacore.jpashop.dto.CreateMemberRequest;
import jpacore.jpashop.service.MemberService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @GetMapping("/api/v1/members")
    public List<Member> membersV1(){
        return memberService.findMembers();
    }

    @GetMapping("/api/v2/members")
    public Result memberV2(){
        List<MemberDTO> findMemberDto = memberService.findMembers().stream()
                .map((member) -> new MemberDTO(member.getName()))
                .collect(Collectors.toList());
        return new Result(findMemberDto.size() , findMemberDto) ;
    }

    @PostMapping("/api/v1/members")
    public CreateMemberReponse saveMemberV1(@RequestBody @Valid Member member) {
        Long joinId = memberService.join(member);
        return new CreateMemberReponse(joinId);
    }

    @PostMapping("/api/v2/members")
    public testReponse saveMemberV2(@RequestBody @Valid TestRequest request){
        Member member = new Member();
        member.setName(request.getName());
        Long joinid = memberService.join(member);
        return new testReponse(joinid);
    }

    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id , @RequestBody @Valid UpdateMemberRequest request){
        memberService.update(id, request.getName());
        Member one = memberService.findOne(id);
        return new UpdateMemberResponse(one.getId(), one.getName());
    }


    @Getter
    @Setter
    class testReponse {
        private Long id;

        public testReponse(Long joinid) {
            this.id = joinid;
        }
    }

    @Getter
    @Setter
    static class TestRequest {
        private String name;
    }

    @Getter
    @Setter
    static class UpdateMemberRequest {
        private String name;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    private class UpdateMemberResponse {
        private Long id;
        private String name;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    class Result<T> {
        private int count;
        private T data;
    }
    @Data
    @AllArgsConstructor
    static class MemberDTO{
        private String name;
    }
}
