package study.springboot.service.web;

import org.springframework.stereotype.Controller;
import study.springboot.domain.member.Member;
import study.springboot.repository.MemberRepository;

@Controller
public class LoginService {

    private final MemberRepository memberRepository;


    public LoginService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member login(Integer loginId, String password){
        return memberRepository.findByLoginId(loginId).filter(m -> m.getMemberPassword().equals(password)).orElse(null);
    }


}
