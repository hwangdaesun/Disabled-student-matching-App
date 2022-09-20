package study.springboot.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.springboot.domain.board.Board;
import study.springboot.domain.board.Range;
import study.springboot.repository.BoardRepository;
import study.springboot.repository.MemberRepository;
import study.springboot.service.board.BoardService;
import study.springboot.service.member.MemberService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardService boardService;

    @BeforeEach
    void beforeEach(){
        boardService.deleteAllBoard();
    }

    @Test
    void check() {
        assertThrows(NoSuchElementException.class, ()->boardService.check(192311));
    }

    @Test
    void getBoardList() {
        Board board = new Board();
        board.setId(193832);
        board.setTitle("도움");
        board.setWriter("익명");
        board.setContent("도와주실 사람 구합니다.");
        board.setRange(Range.ONLY_NORMAL);
        board.setLocalDateTime(LocalDateTime.now());

        Board board1 = new Board();
        board1.setId(158933);
        board1.setTitle("도움22");
        board1.setWriter("익명22");
        board1.setContent("도와주실 사람 구합니다.33");
        board1.setRange(Range.ONLY_UNNORMAL);
        board1.setLocalDateTime(LocalDateTime.now());

        Board board2 = new Board();
        board2.setId(174730);
        board2.setTitle("도움33");
        board2.setWriter("익명33");
        board2.setContent("도와주실 사람 구합니다.00000");
        board2.setRange(Range.ALL);
        board2.setLocalDateTime(LocalDateTime.now());

        boardService.insertBoard(board);
        boardService.insertBoard(board1);
        boardService.insertBoard(board2);

        List<Board> boardList = boardService.getBoardList();

        Assertions.assertThat(boardList.size()).isEqualTo(3);

    }

    @Test
    void getNormalBoards() {
        Board board = new Board();
        board.setId(193832);
        board.setTitle("도움");
        board.setWriter("익명");
        board.setContent("도와주실 사람 구합니다.");
        board.setRange(Range.ONLY_NORMAL);
        board.setLocalDateTime(LocalDateTime.now());

        Board board1 = new Board();
        board1.setId(158933);
        board1.setTitle("도움22");
        board1.setWriter("익명22");
        board1.setContent("도와주실 사람 구합니다.33");
        board1.setRange(Range.ONLY_UNNORMAL);
        board1.setLocalDateTime(LocalDateTime.now());

        Board board2 = new Board();
        board2.setId(174730);
        board2.setTitle("도움33");
        board2.setWriter("익명33");
        board2.setContent("도와주실 사람 구합니다.00000");
        board2.setRange(Range.ALL);
        board2.setLocalDateTime(LocalDateTime.now());

        boardService.insertBoard(board);
        boardService.insertBoard(board1);
        boardService.insertBoard(board2);

        List<Board> normalBoards = boardService.getNormalBoards();

        Assertions.assertThat(normalBoards.size()).isEqualTo(1);
    }

    @Test
    void getUnNormalBoards() {
        Board board = new Board();
        board.setId(193832);
        board.setTitle("도움");
        board.setWriter("익명");
        board.setContent("도와주실 사람 구합니다.");
        board.setRange(Range.ONLY_NORMAL);
        board.setLocalDateTime(LocalDateTime.now());

        Board board1 = new Board();
        board1.setId(158933);
        board1.setTitle("도움22");
        board1.setWriter("익명22");
        board1.setContent("도와주실 사람 구합니다.33");
        board1.setRange(Range.ONLY_UNNORMAL);
        board1.setLocalDateTime(LocalDateTime.now());

        Board board2 = new Board();
        board2.setId(174730);
        board2.setTitle("도움33");
        board2.setWriter("익명33");
        board2.setContent("도와주실 사람 구합니다.00000");
        board2.setRange(Range.ALL);
        board2.setLocalDateTime(LocalDateTime.now());

        boardService.insertBoard(board);
        boardService.insertBoard(board1);
        boardService.insertBoard(board2);

        List<Board> unNormalBoards = boardService.getUnNormalBoards();
        Assertions.assertThat(unNormalBoards.size()).isEqualTo(1);
    }

    @Test
    void insertBoard() {
        Board board = new Board();
        board.setId(193832);
        board.setTitle("도움");
        board.setWriter("익명");
        board.setContent("도와주실 사람 구합니다.");
        board.setRange(Range.ONLY_NORMAL);
        board.setLocalDateTime(LocalDateTime.now());

        boardService.insertBoard(board);

        Board board1 = boardService.getBoard(board.getId()).get();

        Assertions.assertThat(board1).isEqualTo(board);
    }

    @Test
    void updateBoard() {
        Board board = new Board();
        board.setId(193832);
        board.setTitle("도움");
        board.setWriter("익명");
        board.setContent("도와주실 사람 구합니다.");
        board.setRange(Range.ONLY_NORMAL);
        board.setLocalDateTime(LocalDateTime.now());

        boardService.insertBoard(board);

        board.setTitle("제목 바꿈");
        boardService.insertBoard(board);

        Assertions.assertThat(board.getTitle()).isEqualTo(boardService.getBoard(board.getId()).get().getTitle());
    }

    @Test
    void deleteBoard() {
        Board board = new Board();
        board.setId(193832);
        board.setTitle("도움");
        board.setWriter("익명");
        board.setContent("도와주실 사람 구합니다.");
        board.setRange(Range.ONLY_NORMAL);
        board.setLocalDateTime(LocalDateTime.now());

        boardService.insertBoard(board);
        boardService.deleteBoard(board);

        assertThrows(NoSuchElementException.class, ()->boardService.check(193832));
    }
}