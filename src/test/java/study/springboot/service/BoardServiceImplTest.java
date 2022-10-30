package study.springboot.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.springboot.controller.board.BoardForm;
import study.springboot.domain.board.Board;
import study.springboot.domain.board.Range;
import study.springboot.domain.member.Grade;
import study.springboot.domain.member.Member;
import study.springboot.service.board.BoardService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardServiceImplTest {

    private final BoardService boardService;

    @Autowired
    public BoardServiceImplTest(BoardService boardService) {
        this.boardService = boardService;
    }

    @AfterEach
    void clear(){
        boardService.deleteAllBoard();
    }

    @Test
    void check() {
        assertThrows(NoSuchElementException.class, ()->boardService.check(192311));
    }

    @Test
    void getBoardList() {
        Board board = new Board();
        board.setBoardId(1);
        board.setMemberId(193849);
        board.setTitle("안녕하");
        board.setWriter("아아");
        board.setContent("하 제발");
        board.setLocalDateTime(LocalDateTime.now());
        board.setRange(Range.ALL);

        Board board1 = new Board();
        board1.setBoardId(2);
        board1.setMemberId(182249);
        board1.setTitle("안녕");
        board1.setWriter("야야야야야야");
        board1.setContent("이거 테스트 한방에 되는 거 없나");
        board1.setLocalDateTime(LocalDateTime.now());
        board1.setRange(Range.ONLY_UNNORMAL);

        Board board2 = new Board();
        board2.setBoardId(3);
        board2.setMemberId(222899);
        board2.setTitle("힙합");
        board2.setWriter("힙찔이");
        board2.setContent("인생 ㅎ비합");
        board2.setLocalDateTime(LocalDateTime.now());
        board2.setRange(Range.ONLY_UNNORMAL);

//        boardService.insertBoard(board);
//        boardService.insertBoard(board1);
//        boardService.insertBoard(board2);
        List<Board> boardList = boardService.getBoardList();

        Assertions.assertThat(boardList.size()).isEqualTo(3);

    }

    @Test
    void getNormalBoards() {
        Board board = new Board();
        board.setBoardId(1);
        board.setMemberId(193849);
        board.setTitle("안녕하");
        board.setWriter("아아");
        board.setContent("하 제발");
        board.setLocalDateTime(LocalDateTime.now());
        board.setRange(Range.ALL);

        Board board1 = new Board();
        board1.setBoardId(2);
        board1.setMemberId(182249);
        board1.setTitle("안녕");
        board1.setWriter("야야야야야야");
        board1.setContent("이거 테스트 한방에 되는 거 없나");
        board1.setLocalDateTime(LocalDateTime.now());
        board1.setRange(Range.ONLY_NORMAL);

       // boardService.insertBoard(board);
     //   boardService.insertBoard(board1);

        List<Board> normalBoards = boardService.getNormalBoards();

        Assertions.assertThat(normalBoards.size()).isEqualTo(1);
    }

    @Test
    void getUnNormalBoards() {
        Board board = new Board();
        board.setBoardId(1);
        board.setMemberId(193849);
        board.setTitle("안녕하");
        board.setWriter("아아");
        board.setContent("하 제발");
        board.setLocalDateTime(LocalDateTime.now());
        board.setRange(Range.ALL);

        Board board1 = new Board();
        board1.setBoardId(2);
        board1.setMemberId(182249);
        board1.setTitle("안녕");
        board1.setWriter("야야야야야야");
        board1.setContent("이거 테스트 한방에 되는 거 없나");
        board1.setLocalDateTime(LocalDateTime.now());
        board1.setRange(Range.ONLY_UNNORMAL);

        //boardService.insertBoard(board);
     ///   boardService.insertBoard(board1);


        List<Board> unNormalBoards = boardService.getUnNormalBoards();

        Assertions.assertThat(unNormalBoards.size()).isEqualTo(1);
    }

    @Test
    void updateBoard() {
        BoardForm boardForm = new BoardForm();
        boardForm.setTitle("수정");
        boardForm.setWriter("수정");
        boardForm.setContent("수정");
        boardForm.setLocalDateTime(LocalDateTime.now());
        boardForm.setRange(Range.ALL);

        Board board = new Board();
        board.setBoardId(1);
        board.setMemberId(193849);
        board.setTitle("안녕하");
        board.setWriter("아아");
        board.setContent("하 제발");
        board.setLocalDateTime(LocalDateTime.now());
        board.setRange(Range.ALL);

        Member member = new Member();
        member.setMemberName("대선");
        member.setMemberId(193849);
        member.setMemberPassword("123@");
        member.setMemberNumber("01032431257");
        member.setSex(1);
        member.setGrade(Grade.NORMAL);

        boardService.insertBoard(boardForm,member);
        boardService.updateBoard(boardForm,1);

        Assertions.assertThat(boardForm.getTitle()).isEqualTo(boardService.getBoard(1).get().getTitle());


    }

//    @Test
//    void deleteBoard() {
//        Board board = new Board();
//        board.setBoardId(1);
//        board.setMemberId(193849);
//        board.setTitle("안녕하");
//        board.setWriter("아아");
//        board.setContent("하 제발");
//        board.setLocalDateTime(LocalDateTime.now());
//        board.setRange(Range.ALL);
//
// //       boardService.insertBoard(board);
//
//        boardService.deleteBoard(board);
//
//        assertThrows(NoSuchElementException.class, ()->boardService.check(1));
//
//    }
}