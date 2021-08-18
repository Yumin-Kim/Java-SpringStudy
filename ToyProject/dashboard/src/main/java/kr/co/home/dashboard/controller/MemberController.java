package kr.co.home.dashboard.controller;

import kr.co.home.dashboard.dto.MemberForm;
import kr.co.home.dashboard.dto.Res;
import kr.co.home.dashboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/jsp")
    public ModelAndView HomeJSP(Model model) {
        ModelAndView mav = new ModelAndView("test");
        mav.addObject("name", "goddaehee");
        List<String> testList = new ArrayList<String>();
        testList.add("a");
        testList.add("b");
        testList.add("c");
        mav.addObject("list", testList);
        return mav;
    }

    @GetMapping("/page")
    public String HomeJSP_String(Model model) {
        return "member/index";
    }

    @GetMapping("/page/create")
    public String createMember() {
        return "member/create";
    }

    @GetMapping("/page/list")
    public String getMember() {
        return "member/list";
    }


    @PostMapping("/create.do")
    public String createMember(
            @Validated({MemberForm.MemberCreateForm.class})
                    MemberForm memberForm
    ) {

        log.info("memberForm.getName() = {}", memberForm.getName());
        String message = memberService.createMember(memberForm);
        Res<String> success = Res.isOk(message);

        return "redirect:member/index";
    }

}
