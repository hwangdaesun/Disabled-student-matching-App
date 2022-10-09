package study.springboot.repository;

import study.springboot.domain.board.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {

    public Optional<Board> getBoard(Integer boardId);

    public List<Board> getBoardList();

    public List<Board> findByTitleContaining(String keyword);

    public void insertBoard(Board board);

    public void updateBoard(Board board);

    public void deleteBoard(Board board);

    public void deleteAllBoard();


}


