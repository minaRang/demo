package minaboard.minab.controller;

import minaboard.minab.domain.BoardDTO;
import minaboard.minab.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write.do")
    public String openBoardWrite(@RequestParam(value = "idx", required = false)Long idx,Model model){
        //게시글 리스트 페이지에서 게시글 등록 페이지로 이동할 경우
        //게시글 번호는 null로 전송(새로운 게시글 등록은 게시글 번호가 필요X->required를 false)
        if(idx==null){
            //idx가 null이면 비어있는 객체 전달
            model.addAttribute("board",new BoardDTO());
        }else{
            //idx가 전송되면 getBoardDetail(게시글 정보를 포함하고 있는 객체 전달)
            BoardDTO boardDTO = boardService.getBoardDetail(idx);
            if(boardDTO==null){
                return "redirect:/board/list.do";
            }
            model.addAttribute("board",boardDTO);
        }
        return "board/write";
    }
    @PostMapping("/board/register.do")
    public String registerBoard(final BoardDTO boardDTO){
        try{
            boolean isRegistered = boardService.registerBoard(boardDTO);
            if(isRegistered=false) {
            }
            }catch(DataAccessException e){
            }catch(Exception e){

            }
            return "redirect:/board/list.do";
        }
    @GetMapping("/board/list.do")
    public String openBoardList(Model model){
        List<BoardDTO> boardList = boardService.getBoardList();
        model.addAttribute("boardList",boardList);
        return "board/list";
    }
    }

