package study.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import study.springboot.domain.member.Member;
import study.springboot.service.web.LoginService;

@Controller
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping(value="/login")
    public String login(){
        return "members/login";
    }

    @PostMapping(value="/login")
    public String loginMember(@ModelAttribute LoginForm loginForm){
        Member loginMember = loginService.login(loginForm.getLoginId(), loginForm.getLoginPassword());
        System.out.println("loginMemberId = " + loginForm.getLoginId() + "loginMemberPassword =" + loginForm.getLoginPassword());
        if(loginMember == null){
            return "members/login";
        }
        return "boards/main";
    }

    @GetMapping(value = "/logout")
    public String logout(){
        return "/";
    }
}
