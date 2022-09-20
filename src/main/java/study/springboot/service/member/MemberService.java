package study.springboot.service.member;

import study.springboot.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    Integer join(Member member);
    Optional<Member> findMember(Integer memberId);

    List<Member> findMembers();

    void update(Member member);

     void delete(Member member);

    Member check(Integer memberId);
}
