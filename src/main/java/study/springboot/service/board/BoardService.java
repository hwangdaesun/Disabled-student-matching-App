package study.springboot.service.board;

import study.springboot.domain.board.Board;

import java.util.List;
import java.util.Optional;

public interface BoardService {

    Board check(Integer boardId);
    List<Board> getBoardList();
    Optional<Board> getBoard(Integer boardId);

    List<Board> getNormalBoards();

    List<Board> getUnNormalBoards();
    void insertBoard(Board board);
    void updateBoard(Board board);
    void deleteBoard(Board board);

    void deleteAllBoard();
}
