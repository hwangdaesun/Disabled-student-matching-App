package study.springboot.controller.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import study.springboot.controller.member.SessionConstants;
import study.springboot.domain.board.Board;
import study.springboot.domain.member.Member;
import study.springboot.service.board.BoardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }



    @GetMapping("/list")
    //전체 게시물
    public String getBoardList(Model model, @SessionAttribute(SessionConstants.LOGIN_MEMBER)Object loginMember){
        List<Board> boardList = boardService.getBoardList();
        model.addAttribute("boards",boardList);
        model.addAttribute("member", loginMember);
        return "boards/main";
    }

    @GetMapping("/new")
    public String newBoard(Model model,@SessionAttribute(SessionConstants.LOGIN_MEMBER)Object loginMember){
        model.addAttribute("member",loginMember);
        return "boards/new";
    }

    @PostMapping("/new")
    public <loginMember> String writeBoard(BoardForm boardForm, @SessionAttribute(SessionConstants.LOGIN_MEMBER)Object loginMember){
        boardService.insertBoard(boardForm,(Member)loginMember);
        return "redirect:/boards/list";
    }

    @GetMapping("/detail/{no}")
    // @PathVariable 공부
    public String detailBoard(@PathVariable("no") Integer boardId,Model model){
        Board board = boardService.getBoard(boardId).get();
        model.addAttribute("board",board);
        return "boards/detail";
    }



}
