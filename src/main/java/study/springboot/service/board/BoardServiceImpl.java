package study.springboot.service.board;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.springboot.controller.board.BoardForm;
import study.springboot.domain.board.Board;
import study.springboot.domain.board.Range;
import study.springboot.domain.member.Member;
import study.springboot.repository.BoardRepository;

import java.sql.ResultSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static study.springboot.domain.board.Range.ONLY_NORMAL;
import static study.springboot.domain.board.Range.ONLY_UNNORMAL;

@Service
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


    public List<Board> getNormalBoards() {
        return getBoardList().stream().filter(board -> board.getRange() == ONLY_NORMAL).collect(Collectors.toList());
    }

    public List<Board> getUnNormalBoards() {
        return getBoardList().stream().filter(board -> board.getRange() == ONLY_UNNORMAL).collect(Collectors.toList());
    }

    @Override
    public void insertBoard(BoardForm boardForm, Member loginMember) {
        Board board = new Board();
        board.setMemberId(loginMember.getMemberId());
        board.setTitle(boardForm.getTitle());
        board.setWriter(boardForm.getWriter());
        board.setRange(boardForm.getRange());
        board.setLocalDateTime(boardForm.getLocalDateTime());
        board.setContent(boardForm.getContent());;
        boardRepository.insertBoard(board);
    }

    @Override
    public void updateBoard(Board board) {
        check(board.getBoardId());
        boardRepository.updateBoard(board);
    }

    @Override
    public void deleteBoard(Board board) {
        check(board.getBoardId());
        boardRepository.deleteBoard(board);

    }

    @Override
    public void deleteAllBoard() {
        boardRepository.deleteAllBoard();
    }
}
