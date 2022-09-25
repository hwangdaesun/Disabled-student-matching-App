package study.springboot.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import study.springboot.domain.member.Grade;
import study.springboot.domain.member.Member;
import java.util.List;

@SpringBootTest
@Transactional(rollbackFor = Exception.class)
class JdbcTemplateMemberRepositoryTest {

    MemberRepository memberRepository;

    @Autowired
    JdbcTemplateMemberRepositoryTest(JdbcTemplateMemberRepository jdbcTemplateMemberRepository) {
        this.memberRepository = jdbcTemplateMemberRepository;
    }

    @AfterEach
    void clear(){
        memberRepository.deleteAll();
    }

    @Test
    void save(){
        // given
        Member member = new Member();
        member.setMemberName("대선");
        member.setMemberId(183300);
        member.setMemberPassword("123@");
        member.setMemberNumber("01032431257");
        member.setSex(1);
        member.setGrade(Grade.NORMAL);
        memberRepository.save(member);

        //when
        Member result = memberRepository.findById(member.getMemberId()).get();

        // then
        Assertions.assertEquals(member.getMemberName(), result.getMemberName());
    }

    @Test
    void findAll() {
        Member member = new Member();
        member.setMemberName("정수");
        member.setMemberId(185701);
        member.setMemberPassword("123455%%");
        member.setMemberNumber("01098491348");
        member.setSex(1);
        member.setGrade(Grade.UNNORMAL);
        memberRepository.save(member);

        Member member1 = new Member();
        member1.setMemberName("기철");
        member1.setMemberId(183940);
        member1.setMemberPassword("123@");
        member1.setMemberNumber("01032431257");
        member1.setSex(1);
        member1.setGrade(Grade.NORMAL);
        memberRepository.save(member1);

        List<Member> list = memberRepository.findAll();

        Assertions.assertEquals(2,list.size());
    }


    @Test
    void update() {
        Member member1 = new Member();
        member1.setMemberName("대선");
        member1.setMemberId(193394);
        member1.setMemberPassword("123@");
        member1.setMemberNumber("01032431257");
        member1.setSex(1);
        member1.setGrade(Grade.NORMAL);
        memberRepository.save(member1);

        member1.setMemberPassword("@@33344");
        memberRepository.update(member1);

        Assertions.assertEquals(member1.getMemberPassword(),memberRepository.findById(member1.getMemberId()).get().getMemberPassword());
    }

    @Test
    void delete() {
        Member member1 = new Member();
        member1.setMemberName("대선");
        member1.setMemberId(193394);
        member1.setMemberPassword("123@");
        member1.setMemberNumber("01032431257");
        member1.setSex(1);
        member1.setGrade(Grade.NORMAL);
        memberRepository.save(member1);

        memberRepository.delete(member1);

        boolean result = memberRepository.findById(member1.getMemberId()).isPresent();
        Assertions.assertEquals(false,result);

        }
}