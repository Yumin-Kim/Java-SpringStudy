package jpacore.jpashop.service;

import jpacore.jpashop.domain.Address;
import jpacore.jpashop.domain.Job;
import jpacore.jpashop.domain.Member;
import jpacore.jpashop.dto.MemberForm;
import jpacore.jpashop.repository.dto.MemberDto;
import jpacore.jpashop.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


//TODO 개발 비즈니스 요구 사항 - 시큐리티에서 진행
// 1. 로그아웃 처리
// 2. 로그인 처리

@Service
@RequiredArgsConstructor
@Transactional
public class RemoteService {
    private final MemberRepository memberRepository;

    public MemberDto signup(MemberForm memberForm){

        memberRepository.findByNickname(memberForm.getNickname())
                .ifPresent(member -> {
                    throw new IllegalStateException(member.getNickname() + "는 존재하는 nickname입니다");
                });
        memberRepository.findByEmail(memberForm.getEmail())
                .ifPresent(member ->{
                    throw new IllegalStateException(member.getEmail() + "존재하는 이메일 입니다");
                } );
        Address address = Address.of(memberForm.getCity(), memberForm.getStreet(), memberForm.getCitycode());

        List<Job> jobs = memberForm.getJobs().stream()
                .map(Job::createJob).collect(Collectors.toList());

        Member member = Member.createMember(
                memberForm.getName(),
                memberForm.getNickname(),
                memberForm.getPassword(),
                memberForm.getEmail(),
                memberForm.getAge(),
                address,
                jobs
        );
        Member saveMember = memberRepository.save(member);
        new MemberDto(saveMember);
        return null;
    }
}
