//package study.springboot.repository;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//import study.springboot.domain.board.Board;
//import study.springboot.domain.board.Range;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//@SpringBootTest
//@Transactional
//public class MemoryBoardRepositoryTest {
//    @Autowired
//    private BoardRepository boardRepository;
//
//    @Test
//    void insertBoard(){
//        Board board = new Board();
//        board.setId(193832);
//        board.setTitle("도움");
//        board.setWriter("익명");
//        board.setContent("도와주실 사람 구합니다.");
//        board.setRange(Range.ONLY_NORMAL);
//        board.setLocalDateTime(LocalDateTime.now());
//        boardRepository.insertBoard(board);
//        Assertions.assertWith(board).isEqualTo(boardRepository.getBoard(board.getId()).get());
//    }
//
//    @Test
//    void getBoard(){
//        Optional<Board> board = boardRepository.getBoard(193834);
//        Assertions.assertThat(board.isPresent()).isEqualTo(false);
//
//    }
//
//
//    @Test
//    void getBoardList(){
//
//        Board board = new Board();
//        board.setId(193832);
//        board.setTitle("도움");
//        board.setWriter("익명");
//        board.setContent("도와주실 사람 구합니다.");
//        board.setRange(Range.ONLY_NORMAL);
//        board.setLocalDateTime(LocalDateTime.now());
//        boardRepository.insertBoard(board);
//
//        Board board1 = new Board();
//        board1.setId(203811);
//        board1.setTitle("rr");
//        board1.setWriter("익명22");
//        board1.setContent("도와주실 사람 구합니다.222");
//        board1.setRange(Range.ONLY_NORMAL);
//        board1.setLocalDateTime(LocalDateTime.now());
//        boardRepository.insertBoard(board1);
//
//        List<Board> boardList = boardRepository.getBoardList();
//        Assertions.assertThat(boardList.size()).isEqualTo(2);
//    }
//
//    @Test
//    void updateBoard(){
//        Board board = new Board();
//        board.setId(193832);
//        board.setTitle("도움");
//        board.setWriter("익명");
//        board.setContent("도와주실 사람 구합니다.");
//        board.setRange(Range.ONLY_NORMAL);
//        board.setLocalDateTime(LocalDateTime.now());
//        boardRepository.insertBoard(board);
//
//        board.setTitle("제목 바꿈");
//        boardRepository.updateBoard(board);
//
//        Assertions.assertThat(board.getTitle()).isEqualTo(boardRepository.getBoard(board.getId()).get().getTitle());
//    }
//
//    @Test
//    void deleteBoard(){
//        Board board = new Board();
//        board.setId(193832);
//        board.setTitle("도움");
//        board.setWriter("익명");
//        board.setContent("도와주실 사람 구합니다.");
//        board.setRange(Range.ONLY_NORMAL);
//        board.setLocalDateTime(LocalDateTime.now());
//        boardRepository.insertBoard(board);
//
//        boardRepository.deleteBoard(board);
//
//        Assertions.assertThat(boardRepository.getBoard(193832).isPresent()).isEqualTo(false);
//
//    }
//
//}
