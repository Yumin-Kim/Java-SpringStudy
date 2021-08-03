package tutorial.study.servlet_nolecture.basic.frontcontroller.v3;

import tutorial.study.servlet_nolecture.basic.domain.Member;
import tutorial.study.servlet_nolecture.basic.domain.MemberRepository;
import tutorial.study.servlet_nolecture.basic.frontcontroller.ModelView;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3{

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> all = memberRepository.findAll();
        ModelView members = new ModelView("members");
        members.getModel().put("members", all);
        return members;
    }
}
