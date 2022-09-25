package study.springboot.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import study.springboot.domain.member.Member;
import study.springboot.repository.*;
import study.springboot.service.board.BoardService;
import study.springboot.service.board.BoardServiceImpl;
import study.springboot.service.member.MemberService;
import study.springboot.service.member.MemberServiceImpl;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class SpringConfig {

    @Autowired
    private DataSource dataSource;

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
        return new JdbcTemplateMemberRepository(dataSource);
    }

    @Bean
    public BoardRepository boardRepository(){
        return new JdbcTemplateBoardRepository(dataSource);
    }


}
