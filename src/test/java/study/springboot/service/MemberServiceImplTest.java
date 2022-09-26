package study.springboot.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.springboot.domain.member.Grade;
import study.springboot.domain.member.Member;
import study.springboot.service.member.MemberService;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceImplTest {

    private final MemberService memberService;

    @Autowired
    public MemberServiceImplTest(MemberService memberService) {
        this.memberService = memberService;
    }

    @AfterEach
    void clear(){
        memberService.deleteAll();
    }

    @Test
    void join() {
        Member member = new Member();
        member.setMemberName("대선");
        member.setMemberId(183300);
        member.setMemberPassword("123@");
        member.setMemberNumber("01032431257");
        member.setSex(1);
        member.setGrade(Grade.NORMAL);
        Integer id = memberService.join(member);
        assertThat(id).isEqualTo(member.getMemberId());
    }

    @Test
    void validDuplicateMember() {
        Member member = new Member();
        member.setMemberName("대선");
        member.setMemberId(183300);
        member.setMemberPassword("123@");
        member.setMemberNumber("01032431257");
        member.setSex(1);
        member.setGrade(Grade.NORMAL);
        memberService.join(member);

        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> memberService.join(member));

        assertThat(exception.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

    @Test
    void check() {
        assertThrows(NoSuchElementException.class, ()->memberService.check(192311));
    }

    @Test
    void findMembers() {
        Member member = new Member();
        member.setMemberName("대선");
        member.setMemberId(183300);
        member.setMemberPassword("123@");
        member.setMemberNumber("01032431257");
        member.setSex(1);
        member.setGrade(Grade.NORMAL);
        memberService.join(member);

        Member member1 = new Member();
        member1.setMemberName("철수");
        member1.setMemberId(204833);
        member1.setMemberPassword("jion12");
        member1.setMemberNumber("01084957492");
        member1.setSex(0);
        member1.setGrade(Grade.NORMAL);
        memberService.join(member1);

        List<Member> list = memberService.findMembers();
        assertEquals(2,list.size());
    }

    @Test
    void findMember() {
        Member member = new Member();
        member.setMemberName("대선");
        member.setMemberId(183300);
        member.setMemberPassword("123@");
        member.setMemberNumber("01032431257");
        member.setSex(1);
        member.setGrade(Grade.NORMAL);
        memberService.join(member);

        Member findMember = memberService.findMember(member.getMemberId()).get();
        assertEquals(member.getMemberName(),findMember.getMemberName());
    }

    @Test
    void update() {

        Member member = new Member();
        member.setMemberName("대선");
        member.setMemberId(183300);
        member.setMemberPassword("123@");
        member.setMemberNumber("01032431257");
        member.setSex(1);
        member.setGrade(Grade.NORMAL);
        memberService.join(member);

        member.setMemberPassword("1234@@");
        try{
            memberService.update(member);
        }catch (NoSuchElementException e){
            System.out.println(1);
            e.printStackTrace();
        }
        assertEquals(member.getMemberPassword(),memberService.findMember(member.getMemberId()).get().getMemberPassword());
    }
}