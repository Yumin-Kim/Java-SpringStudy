package jpastudy.stduy.controller;

import jpastudy.stduy.domain.Member;
import jpastudy.stduy.repository.MemberRepository;
import jpastudy.stduy.repository.custom.MemberRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public Member getMember(@PathVariable("id") Long id) {
        return memberRepository.findById(id).get();
    }

    @GetMapping("/members2/{id}")
    public String getMemberDomin(@PathVariable("id") Member member) {
        return member.getUsername();
    }

    @GetMapping("/members")
    public Page<Member> list(Pageable pageable) {
        Page<Member> all = memberRepository.findAll(pageable);
        return all;
    }

    @GetMapping("/members/list")
    public Page<Member> memberlist(@PageableDefault(size=2) Pageable pageable) {
        Page<Member> all = memberRepository.findAll(pageable);
        return all;
    }

    @PostConstruct
    public void init() {
        memberRepository.save(new Member("MemberA"));
    }

}
