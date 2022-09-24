//package study.springboot.repository;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import study.springboot.domain.member.Grade;
//import study.springboot.domain.member.Member;
//
//import java.util.List;
//
//@SpringBootTest
//public class MemoryMemberRepositoryTest {
//
//
//    @Autowired
//    MemberRepository repository;
//
//    @BeforeEach
//    void beforeEach(){
//        repository.deleteAll();
//    }
//
//
//    @Test
//    void save() {
//        // given
//        Member member = new Member();
//        member.setMemberName("대선");
//        member.setMemberId(194317);
//        member.setMemberPassword("123@");
//        member.setMemberNumber("01032431257");
//        member.setSex(1);
//        member.setGrade(Grade.NORMAL);
//        repository.save(member);
//
//        //when
//        Member result = repository.findById(member.getMemberId()).get();
//
//        // then
//        Assertions.assertEquals(member.getMemberName(), result.getMemberName());
//
//    }
//
//
//    @Test
//    void findAll() {
//        Member member = new Member();
//        member.setMemberName("정수");
//        member.setMemberId(185731);
//        member.setMemberPassword("123455%%");
//        member.setMemberNumber("01098491348");
//        member.setSex(1);
//        member.setGrade(Grade.UNNORMAL);
//        repository.save(member);
//
//        Member member1 = new Member();
//        member1.setMemberName("대선");
//        member1.setMemberId(194317);
//        member1.setMemberPassword("123@");
//        member1.setMemberNumber("01032431257");
//        member1.setSex(1);
//        member1.setGrade(Grade.NORMAL);
//        repository.save(member1);
//
//        List<Member> list = repository.findAll();
//
//        Assertions.assertEquals(2,list.size());
//    }
//
//
//    @Test
//    void update() {
//        Member member1 = new Member();
//        member1.setMemberName("대선");
//        member1.setMemberId(194317);
//        member1.setMemberPassword("123@");
//        member1.setMemberNumber("01032431257");
//        member1.setSex(1);
//        member1.setGrade(Grade.NORMAL);
//        repository.save(member1);
//
//        member1.setMemberPassword("@@33344");
//        repository.update(member1);
//
//        Assertions.assertEquals(member1.getMemberPassword(),repository.findById(member1.getMemberId()).get().getMemberPassword());
//    }
////
//    @Test
//    void delete() {
//        Member member1 = new Member();
//        member1.setMemberName("대선");
//        member1.setMemberId(194317);
//        member1.setMemberPassword("123@");
//        member1.setMemberNumber("01032431257");
//        member1.setSex(1);
//        member1.setGrade(Grade.NORMAL);
//        repository.save(member1);
//
//        repository.delete(member1);
//
//        boolean result = repository.findById(member1.getMemberId()).isPresent();
//
//        Assertions.assertEquals(false,result);
//    }
//}
