package study.springboot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import study.springboot.repository.BoardRepository;
import study.springboot.repository.JdbcTemplateBoardRepository;
import study.springboot.repository.JdbcTemplateMemberRepository;
import study.springboot.repository.MemberRepository;
import study.springboot.service.board.BoardService;
import study.springboot.service.board.BoardServiceImpl;
import study.springboot.service.member.MemberService;
import study.springboot.service.member.MemberServiceImpl;

import javax.sql.DataSource;

@Configuration
@ComponentScan
public class SpringConfig {
    private DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}
