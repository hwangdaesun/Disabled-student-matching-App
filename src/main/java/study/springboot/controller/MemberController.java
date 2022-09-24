package study.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import study.springboot.domain.member.Member;
import study.springboot.service.member.MemberService;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value="/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping(value = "/members/new")
    public String createMember(MemberForm memberForm, Member member){
        member.setSex(memberForm.getSex());
        member.setMemberPassword(memberForm.getMemberPassword());
        member.setMemberName(memberForm.getMemberName());
        member.setMemberNumber(memberForm.getMemberNumber());
        member.setGrade(memberForm.getGrade());
        memberService.join(member);
        return "redirect:/";
    }



}
