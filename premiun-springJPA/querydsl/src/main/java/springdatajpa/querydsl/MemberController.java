package springdatajpa.querydsl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import springdatajpa.querydsl.member.dto.MemberSearchCondition;
import springdatajpa.querydsl.member.dto.MemberTeamDto;
import springdatajpa.querydsl.member.repository.MemberJpaRepository;
import springdatajpa.querydsl.member.repository.MemberRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberJpaRepository memberJpaRepository;
    private final MemberRepository memberRepository;

    @GetMapping("/v1/members")
    public List<MemberTeamDto> searchMemberV1(@RequestBody MemberSearchCondition condition) {
        return memberJpaRepository.searchCoditionMember(condition);
    }

    @GetMapping("/page/v1/members")
    public Page<MemberTeamDto> searchMemberPagingV1(MemberSearchCondition condition, Pageable pageable) {
        return memberRepository.searchCoditionMemberPageCount(condition,pageable);
    }

    @GetMapping("/page/v2/members")
    public Page<MemberTeamDto> searchMemberPagingV2(MemberSearchCondition condition, Pageable pageable) {
        return memberRepository.searchCoditionMemberPageCountCompare(condition,pageable);
    }


}
