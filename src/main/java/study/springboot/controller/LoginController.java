package study.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import study.springboot.domain.member.Member;
import study.springboot.service.web.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public String loginMember(@ModelAttribute LoginForm loginForm, HttpServletRequest request, Model model){
        Member loginMember = loginService.login(loginForm.getLoginId(), loginForm.getLoginPassword());

        if(loginMember == null){
            return "members/login";
        }

        //로그인 성공하면, 세션에 생성
        HttpSession session = request.getSession();
        session.setAttribute(SessionConstants.LOGIN_MEMBER, loginMember);

        model.addAttribute("member",loginMember);

        return "boards/main";
    }

    @PostMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        // create값을 false로 하면 원래 세션이 있으면 그 세션 반환, 세션 없으면 새로 생성 안함
        if(session != null){
            session.invalidate(); // 세션 날림
        }
        return "home";
    }
}
