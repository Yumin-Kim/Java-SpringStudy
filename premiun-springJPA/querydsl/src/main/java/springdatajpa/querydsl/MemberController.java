package springdatajpa.querydsl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springdatajpa.querydsl.member.dto.MemberSearchCondition;
import springdatajpa.querydsl.member.dto.MemberTeamDto;
import springdatajpa.querydsl.member.repository.MemberJpaRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberJpaRepository memberJpaRepository;

    @GetMapping("/v1/members")
    public List<MemberTeamDto> searchMemberV1(@RequestBody MemberSearchCondition condition) {
        return memberJpaRepository.searchCoditionMember(condition);
    }
}
