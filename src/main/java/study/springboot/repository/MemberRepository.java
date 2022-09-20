package study.springboot.repository;

import study.springboot.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    void save(Member member);
    Optional<Member> findById(Integer memberId);
    List<Member> findAll();
    void update(Member member);
    void delete(Member member);

    void deleteAll();
}
