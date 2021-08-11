package kr.co.home.dashboard.service;

import kr.co.home.dashboard.dao.repository.MemberRepository;
import kr.co.home.dashboard.dao.repository.TeamRepository;
import kr.co.home.dashboard.domain.Member;
import kr.co.home.dashboard.domain.Team;
import kr.co.home.dashboard.dto.MemberForm;
import kr.co.home.dashboard.dto.Res;
import kr.co.home.dashboard.exception.MemberNotFoundException;
import kr.co.home.dashboard.exception.TeamNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    public List<Res.MemberDto> findMembersInfo(int limit, int offset) {
        PageRequest pageAble = PageRequest.of(offset, limit);
        List<Member> findAllMember = memberRepository.findAll(pageAble).getContent();
        return findAllMember.stream()
                .map(member -> new Res.MemberDto(member))
                .collect(toList());
}

    public String createMember(MemberForm memberForm) {
        Member member = memberForm.toEnity();
        Member saveMember = memberRepository.save(member);
        if(saveMember.getName().equals(member.getName())){
            return "success";
        }
        return "error";
    }

    public Res.MemberDto deleteMember(Long userId) {
        Member findMember = memberRepository.findById(userId).orElseThrow(() -> new MemberNotFoundException());
        findMember.updateDeleted();
        return new Res.MemberDto(findMember);
    }

    public Res.MemberDto updateMember(Long userId, MemberForm memberForm) {
        Member findMember = memberRepository.findById(userId).orElseThrow(() -> new MemberNotFoundException());
        findMember.updateMemberInfo(memberForm);
        return new Res.MemberDto(findMember);
    }

}
