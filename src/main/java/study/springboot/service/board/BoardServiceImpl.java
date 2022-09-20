package study.springboot.service.board;


import org.springframework.beans.factory.annotation.Autowired;
import study.springboot.domain.board.Board;
import study.springboot.domain.member.Member;
import study.springboot.repository.BoardRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static study.springboot.domain.board.Range.ONLY_NORMAL;
import static study.springboot.domain.board.Range.ONLY_UNNORMAL;

public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }


    public Board check(Integer boardId) throws NoSuchElementException {
        return boardRepository.getBoard(boardId).orElseThrow();
    }
    @Override
    public List<Board> getBoardList() {
        return boardRepository.getBoardList();
    }

    @Override
    public Optional<Board> getBoard(Integer boardId)  {
        check(boardId);
        return boardRepository.getBoard(boardId);
    }

    @Override
    public List<Board> getNormalBoards() {
        return getBoardList().stream().filter(board -> board.getRange() == ONLY_NORMAL).collect(Collectors.toList());
    }

    @Override
    public List<Board> getUnNormalBoards() {
        return getBoardList().stream().filter(board -> board.getRange() == ONLY_UNNORMAL).collect(Collectors.toList());
    }

    @Override
    public void insertBoard(Board board) {
        boardRepository.insertBoard(board);
    }

    @Override
    public void updateBoard(Board board) {
        check(board.getId());
        boardRepository.updateBoard(board);
    }

    @Override
    public void deleteBoard(Board board) {
        check(board.getId());
        boardRepository.deleteBoard(board);

    }

    @Override
    public void deleteAllBoard() {
        boardRepository.deleteAllBoard();
    }
}
