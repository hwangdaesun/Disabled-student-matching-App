package study.springboot.repository;

import org.springframework.beans.factory.annotation.Autowired;
import study.springboot.domain.board.Board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MemoryBoardRepository implements BoardRepository{

    private final Map<Integer, Board> store;

    @Autowired
    public MemoryBoardRepository() {
        this.store = new HashMap<>();
    }

    @Override
    public Optional<Board> getBoard(Integer boardId) {
        return Optional.ofNullable(store.get(boardId));
    }

    @Override
    public List<Board> getBoardList() {
        return store.values().stream().collect(Collectors.toList());
    }

    @Override
    public void insertBoard(Board board) {
        store.put(board.getId(),board);
    }

    @Override
    public void updateBoard(Board board) {
        store.replace(board.getId(),board);
    }

    @Override
    public void deleteBoard(Board board) {
        store.remove(board.getId());
    }

    @Override
    public void deleteAllBoard() {
        store.clear();
    }
}
