package study.springboot.repository;

import org.springframework.beans.factory.annotation.Autowired;
import study.springboot.domain.member.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MemoryMemberRepository implements MemberRepository{

    private final Map<Integer, Member> store;

    @Autowired
    public MemoryMemberRepository() {
        this.store = new HashMap<>();
    }

    @Override
    public void save(Member member) {
       store.put(member.getMemberId(),member);
    }

    @Override
    public Optional<Member> findById(Integer memberId) {
        return Optional.ofNullable(store.get(memberId));
    }

    @Override
    public List<Member> findAll() {
        return  store.values().stream().collect(Collectors.toList());
    }

    @Override
    public void update(Member member) {
        store.replace(member.getMemberId(), member);
    }

    @Override
    public void delete(Member member) {
        store.remove(member.getMemberId());
    }

    @Override
    public void deleteAll(){
        store.clear();
    }

}
