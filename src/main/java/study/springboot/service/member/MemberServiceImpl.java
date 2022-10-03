package study.springboot.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.springboot.domain.member.Member;
import study.springboot.repository.MemberRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;


    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Integer join(Member member){
        validDuplicateMember(member);
        memberRepository.save(member);
        return member.getMemberId();
    }

    // 중복회원 점검 - id primaryKey
    public void validDuplicateMember(Member member) throws IllegalStateException{
        memberRepository.findById(member.getMemberId()).ifPresent(m-> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");}
        );
    }

    // 회원 존재 확인

    public Member check(Integer memberId) throws NoSuchElementException {
        return memberRepository.findById(memberId).orElseThrow();
    }

    //회원조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findMember(Integer memberId){
        Member validMember = check(memberId);
        return memberRepository.findById(memberId);
    }

    //회원 정보 수정
    public void update(Member member) throws NoSuchElementException {
        check(member.getMemberId());
        memberRepository.update(member);
    }

    //회원 정보 삭제
    public void delete(Member member) throws NoSuchElementException{
        check(member.getMemberId());
        memberRepository.delete(member);
    }

    public void deleteAll(){
        memberRepository.deleteAll();
    }

}
