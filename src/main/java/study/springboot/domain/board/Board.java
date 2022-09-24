package study.springboot.domain.board;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Board {

    // 고유한 아이디(게시판)
    private Integer boardId;
    private String title;
    private String writer;
    private String content;
    private LocalDateTime localDateTime;

    private Range range;
    //공개범위


    public Board(Integer boardId, String title, String writer, String content, Range range) {
        this.localDateTime = LocalDateTime.now();
        this.boardId = boardId;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.range = range;
    }

    public Board(){

    }

    public Integer getId() {
        return boardId;
    }

    public void setId(Integer id) {
        this.boardId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public LocalDateTime getLocalDateTime() {
        return this.localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Range getRange() {
        return this.range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + boardId +
                ", title='" + title + '\'' +
                ", writer='" + writer + '\'' +
                ", content='" + content + '\'' +
                ", localDateTime=" + localDateTime.now() + '\'' +
                ", range=" + range +
                '}';
    }

}

