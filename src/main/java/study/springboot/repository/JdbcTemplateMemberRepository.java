package study.springboot.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import study.springboot.domain.member.Grade;
import study.springboot.domain.member.Member;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;


public class JdbcTemplateMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Member member) {
        jdbcTemplate.update("insert into member(memberId,sex,memberPassword,memberName,memberNumber,grade) values(?,?,?,?,?,?)",member.getMemberId(),member.getSex(),member.getMemberPassword(),member.getMemberName(),member.getMemberNumber(),member.getGrade().toString());
    }

    @Override
    public Optional<Member> findById(Integer memberId) {
        List<Member> list = jdbcTemplate.query("select * from member where memberId = ?", memberRowMapper(), memberId);
        return list.stream().findAny();
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
                Member member = new Member();
                member.setMemberName(rs.getString("memberName"));
                member.setMemberId(rs.getInt("memberId"));
                member.setSex(rs.getInt("sex"));
                member.setMemberPassword(rs.getString("memberPassword"));
                member.setMemberNumber(rs.getString("memberNumber"));
                member.setGrade(Grade.valueOf(rs.getString("grade")));
                return member;
            };
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member",memberRowMapper());
    }

    @Override
    public void update(Member member) {
        jdbcTemplate.update("update member set sex=? ,memberPassword=? ,memberName=?,memberNumber=?, grade=? where memberId=?",member.getSex(),member.getMemberPassword(),member.getMemberName(),member.getMemberNumber(),member.getGrade().toString(),member.getMemberId());
    }

    @Override
    public void delete(Member member) {
        jdbcTemplate.update("delete from member where memberId = ?",member.getMemberId());
    }

    @Override
    public void deleteAll(){
        jdbcTemplate.update("truncate member");
    }
}
