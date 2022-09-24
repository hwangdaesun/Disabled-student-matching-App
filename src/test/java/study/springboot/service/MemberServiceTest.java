//package study.springboot.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import study.springboot.domain.member.Grade;
//import study.springboot.domain.member.Member;
//import study.springboot.repository.MemberRepository;
//import study.springboot.service.member.MemberService;
//
//import java.util.List;
//import java.util.NoSuchElementException;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@SpringBootTest
//public class MemberServiceTest {
//
//
//    @Autowired
//    MemberRepository memberRepository;
//    @Autowired
//    MemberService memberService;
//
//    @BeforeEach
//    void beforeEach(){
//        memberRepository.deleteAll();
//    }
//
//    @Test
//    void join() {
//        Member member = new Member();
//        member.setMemberName("대선");
//        member.setMemberId(194317);
//        member.setMemberPassword("123@");
//        member.setMemberNumber("01032431257");
//        member.setSex(1);
//        member.setGrade(Grade.NORMAL);
//        Integer memberId =  memberService.join(member);
//
//        Member findMember =  memberRepository.findById(memberId).get();
//
//        assertEquals(member.getMemberName(),findMember.getMemberName());
//    }
//
//    @Test
//    void 중복_회원_검증_id(){
//        Member member1 = new Member();
//        member1.setMemberName("대선");
//        member1.setMemberId(194317);
//        member1.setMemberPassword("123@");
//        member1.setMemberNumber("01032431257");
//        member1.setSex(1);
//        member1.setGrade(Grade.NORMAL);
//        memberService.join(member1);
//
//        IllegalStateException exception = assertThrows(IllegalStateException.class,
//                () -> memberService.join(member1));
//        assertThat(exception.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//    }
//
//    @Test
//    void check(){
//        assertThrows(NoSuchElementException.class, ()->memberService.check(192311));
//    }
//
//
//    @Test
//    void check1(){
//        Member member1 = new Member();
//        member1.setMemberName("대선");
//        member1.setMemberId(194317);
//        member1.setMemberPassword("123@");
//        member1.setMemberNumber("01032431257");
//        member1.setSex(1);
//        member1.setGrade(Grade.NORMAL);
//        memberService.join(member1);
//
//        Member validMember = memberService.check(member1.getMemberId());
//        assertEquals(member1.getMemberPassword(),validMember.getMemberPassword());
//        assertEquals(member1.getMemberId(),validMember.getMemberId());
//    }
//
//    @Test
//    void findMembers() {
//        Member member1 = new Member();
//        member1.setMemberName("대선");
//        member1.setMemberId(195437);
//        member1.setMemberPassword("123@");
//        member1.setMemberNumber("01032431257");
//        member1.setSex(1);
//        member1.setGrade(Grade.NORMAL);
//        memberService.join(member1);
//
//        Member member = new Member();
//        member.setMemberName("철수");
//        member.setMemberId(194567);
//        member.setMemberPassword("asdf3@");
//        member.setMemberNumber("01084823402");
//        member.setSex(0);
//        member.setGrade(Grade.NORMAL);
//        memberService.join(member);
//
//        List<Member> list = memberService.findMembers();
//
//        assertEquals(2,list.size());
//    }
////
//    @Test
//    void findMember() {
//        Member member = new Member();
//        member.setMemberName("철수");
//        member.setMemberId(134567);
//        member.setMemberPassword("asdf3@");
//        member.setMemberNumber("01084823402");
//        member.setSex(0);
//        member.setGrade(Grade.NORMAL);
//        memberService.join(member);
//
//        Member findMember = memberService.findMember(member.getMemberId()).get();
//
//        assertEquals(member.getMemberName(),findMember.getMemberName());
//    }
//
//
//    @Test
//    void update() {
//        Member member = new Member();
//        member.setMemberName("철수");
//        member.setMemberId(194567);
//        member.setMemberPassword("asdf3@");
//        member.setMemberNumber("01084823402");
//        member.setSex(0);
//        member.setGrade(Grade.NORMAL);
//        memberService.join(member);
//
//
//        member.setMemberPassword("1234@@");
//        try{
//            memberService.update(member);
//        }catch (NoSuchElementException e){
//            System.out.println(1);
//            e.printStackTrace();
//        }
//        assertEquals(member.getMemberPassword(),memberService.findMember(member.getMemberId()).get().getMemberPassword());
//
//    }
//
//    @Test
//    void delete() {
//        Member member = new Member();
//        member.setMemberName("철수");
//        member.setMemberId(194567);
//        member.setMemberPassword("asdf3@");
//        member.setMemberNumber("01084823402");
//        member.setSex(0);
//        member.setGrade(Grade.NORMAL);
//        memberService.join(member);
//
//        memberService.delete(member);
//
//        assertThrows(NoSuchElementException.class, ()->memberService.check(194567));
//    }
//
//}
