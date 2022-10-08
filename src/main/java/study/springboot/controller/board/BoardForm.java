package study.springboot.controller.board;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import study.springboot.domain.board.Range;

import java.time.LocalDateTime;

@Data
public class BoardForm {

    private String title;
    private String writer;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime localDateTime;
    private Range range;
}


