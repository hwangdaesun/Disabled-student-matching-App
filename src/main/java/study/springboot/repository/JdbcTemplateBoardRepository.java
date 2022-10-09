package study.springboot.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import study.springboot.domain.board.Board;
import study.springboot.domain.board.Range;
import study.springboot.domain.member.Member;


import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTemplateBoardRepository implements BoardRepository {

    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public JdbcTemplateBoardRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Board> getBoard(Integer boardId) {
        List<Board> list = jdbcTemplate.query("select * from board where boardId=?", BoardRowMapper(), boardId);
        return list.stream().findAny();
    }

    private RowMapper<Board> BoardRowMapper() {
        return (rs, rowNum) -> {
           Board board = new Board();
           board.setBoardId(rs.getInt("boardId"));
           board.setMemberId(rs.getInt("memberId"));
           board.setTitle(rs.getString("title"));
           board.setWriter(rs.getString("writer"));
           board.setContent(rs.getString("content"));
           board.setLocalDateTime(rs.getTimestamp("localDateTime").toLocalDateTime());
           board.setRange(Range.valueOf(rs.getString("rangeM")));
           return board;
        };
    }

    public  Timestamp localDateTimeToTimeStamp(LocalDateTime ldt) {
        return Timestamp.valueOf(ldt); // 2018-07-26 01:06:55.323
    }

    @Override
    public List<Board> getBoardList() {
        return jdbcTemplate.query("select * from board",BoardRowMapper());
    }

    @Override
    public List<Board> findByTitleContaining(String keyword) {
        List<Board> list = jdbcTemplate.query("select * from board where title like '%테스트%'", BoardRowMapper());
        return list;
    }

    @Override
    public void insertBoard(Board board) {
        jdbcTemplate.update("insert into board(memberId,title,writer,content,localDateTime,rangeM) values(?,?,?,?,?,?)",board.getMemberId(),board.getTitle(),board.getWriter(),board.getContent(),localDateTimeToTimeStamp(board.getLocalDateTime()),board.getRange().toString());
    }

    @Override
    public void updateBoard(Board board) {
        jdbcTemplate.update("update board set title=?, content=?, rangeM=? where boardId=?",board.getTitle(),board.getContent(),board.getRange().toString(),board.getBoardId());
    }

    @Override
    public void deleteBoard(Board board) {
        jdbcTemplate.update("delete from board where boardId=?",board.getBoardId());
    }

    @Override
    public void deleteAllBoard() {
        jdbcTemplate.update("truncate board");
    }
}
