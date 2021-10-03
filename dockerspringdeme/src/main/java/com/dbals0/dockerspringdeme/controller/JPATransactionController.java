package com.dbals0.dockerspringdeme.controller;

import com.dbals0.dockerspringdeme.domain.Member;
import com.dbals0.dockerspringdeme.repository.MemberRepository;
import com.dbals0.dockerspringdeme.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequestMapping("/jpa")
@Transactional
@RequiredArgsConstructor
public class JPATransactionController {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    /*
    멤버 저장
    팀 저장
    멤버 팀 등록
    팀 확인
    멤버 확인
     */
//    @GetMapping
//    public String getMember(Model model) {
//        List<Member> all =  memberRepository.findAll();
//        if (all.size() > 0) {
//            model.addAttribute("data", all);
//        } else {
//            model.addAttribute("data", "존재하지 않습니다.");
//        }
//
//    }

}
