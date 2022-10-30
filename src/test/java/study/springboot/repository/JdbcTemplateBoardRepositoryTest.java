package study.springboot.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.springboot.domain.board.Board;
import study.springboot.domain.board.Range;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class JdbcTemplateBoardRepositoryTest {

    BoardRepository boardRepository;

    @Autowired
    public JdbcTemplateBoardRepositoryTest(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @AfterEach
    void clear(){
        boardRepository.deleteAllBoard();
    }

    @Test
    void getBoard() {
        Board board = new Board();
        board.setBoardId(1);
        board.setMemberId(194317);
        board.setTitle("도움");
        board.setWriter("익명");
        board.setContent("도와주실 사람 구합니다.");
        board.setRange(Range.ONLY_NORMAL);
        board.setLocalDateTime(LocalDateTime.now());
        boardRepository.insertBoard(board);
        Optional<Board> board1 = boardRepository.getBoard(1);
        Assertions.assertThat(board1.isPresent()).isEqualTo(true);
    }

    @Test
    void getBoardList() {
        Board board = new Board();
        board.setBoardId(1);
        board.setMemberId(194317);
        board.setTitle("도움");
        board.setWriter("익명");
        board.setContent("도와주실 사람 구합니다.");
        board.setRange(Range.ONLY_NORMAL);
        board.setLocalDateTime(LocalDateTime.now());
        boardRepository.insertBoard(board);

        Board board1 = new Board();
        board1.setBoardId(2);
        board1.setMemberId(189401);
        board1.setTitle("도움22");
        board1.setWriter("익명22");
        board1.setContent("도와주실 사람 구합니다.22");
        board1.setRange(Range.ONLY_NORMAL);
        board1.setLocalDateTime(LocalDateTime.now());
        boardRepository.insertBoard(board);

        List<Board> boardList = boardRepository.getBoardList();
        Assertions.assertThat(boardList.size()).isEqualTo(2);
    }

    @Test
    void insertBoard() {
        Board board = new Board();
        board.setBoardId(1);
        board.setMemberId(194317);
        board.setTitle("도움");
        board.setWriter("익명");
        board.setContent("도와주실 사람 구합니다.");
        board.setRange(Range.ONLY_NORMAL);
        board.setLocalDateTime(LocalDateTime.now());
        boardRepository.insertBoard(board);
        Assertions.assertWith(board.getMemberId()).isEqualTo(boardRepository.getBoard(1).get().getMemberId());
        Assertions.assertWith(board.getBoardId()).isEqualTo(boardRepository.getBoard(1).get().getBoardId());
        Assertions.assertWith(board.getTitle()).isEqualTo(boardRepository.getBoard(1).get().getTitle());
        Assertions.assertWith(board.getRange()).isEqualTo(boardRepository.getBoard(1).get().getRange());
        Assertions.assertWith(board.getContent()).isEqualTo(boardRepository.getBoard(1).get().getContent());
    }
//
    @Test
    void updateBoard() {
        Board board = new Board();
        board.setBoardId(1);
        board.setMemberId(194317);
        board.setTitle("도움");
        board.setWriter("익명");
        board.setContent("도와주실 사람 구합니다.");
        board.setRange(Range.ONLY_NORMAL);
        board.setLocalDateTime(LocalDateTime.now());
        boardRepository.insertBoard(board);

        board.setRange(Range.ALL);
        boardRepository.updateBoard(board);

        Assertions.assertThat(board.getRange()).isEqualTo(boardRepository.getBoard(1).get().getRange());

    }

//    @Test
//    void deleteBoard() {
//        Board board = new Board();
//        board.setBoardId(1);
//        board.setMemberId(194317);
//        board.setTitle("도움");
//        board.setWriter("익명");
//        board.setContent("도와주실 사람 구합니다.");
//        board.setRange(Range.ONLY_NORMAL);
//        board.setLocalDateTime(LocalDateTime.now());
//        boardRepository.insertBoard(board);
//
//        boardRepository.deleteBoard(board);
//
//        Assertions.assertThat(boardRepository.getBoard(194317).isPresent()).isEqualTo(false);
//    }

}