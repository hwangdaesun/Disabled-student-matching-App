package study.springboot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.springboot.domain.member.Member;
import study.springboot.repository.BoardRepository;
import study.springboot.repository.MemberRepository;
import study.springboot.repository.MemoryBoardRepository;
import study.springboot.repository.MemoryMemberRepository;
import study.springboot.service.board.BoardService;
import study.springboot.service.board.BoardServiceImpl;
import study.springboot.service.member.MemberService;
import study.springboot.service.member.MemberServiceImpl;

import java.util.Map;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public BoardService boardService(){
        return new BoardServiceImpl(boardRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    @Bean
    public BoardRepository boardRepository(){
        return new MemoryBoardRepository();
    }


}
