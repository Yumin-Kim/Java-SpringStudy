package jpacore.jpashop;

import jpacore.jpashop.domain.Address;
import jpacore.jpashop.domain.Job;
import jpacore.jpashop.domain.Member;
import jpacore.jpashop.dto.MemberForm;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

//@Component
public class InitData {

//    @PostConstruct
//    public void init(){
//
//    }

    private MemberForm makeMemberForm(String name, String nickname, String email, List<String> jobs1){
        return new MemberForm("city", "street", "citycode1", name, nickname, "123", email, false, 10, jobs1,1000);
    }

    private Member makeMemberInfo(MemberForm memberForm) {
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
        return member;
    }

}
