package com.example.miniweb.controller;

import com.example.miniweb.domain.Board;
import com.example.miniweb.dto.BoardDto;
import com.example.miniweb.security.service.CustomUserDetails;
import com.example.miniweb.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("")
    public  String readAllBoard(Model model,
                                @PageableDefault(sort = {"id"},
                                        direction = Sort.Direction.DESC,
                                        size = 3) Pageable pageable){
        log.info("{}", pageable);

        Page<Board> boardList = boardService.readAllBoard(pageable);

        Page<BoardDto> boardDtoList = boardService.toDtoList(boardList);

        int nowPage = boardDtoList.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, boardDtoList.getTotalPages());

        model.addAttribute("boards", boardDtoList);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "board";
    }

    @GetMapping("/write")
    public String writePage(Model model){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        CustomUserDetails customUserDetails = (CustomUserDetails) principal;

        String nickname = ((CustomUserDetails) principal).getNickname();

        model.addAttribute("nickname", nickname);

        return "write";
    }

    @PostMapping("/write")
    public String write(BoardDto boardDto){
        log.info("{}", boardDto);
        boardService.write(boardDto);

        return "redirect:/board";
    }

    @GetMapping("/view/{id}")
    public ModelAndView view(@PathVariable("id") Long id){

        Board board = boardService.read(id);

        boardService.updateViewCount(id);
        String nickname = boardService.read(id).getUser().getNickname();

        ModelAndView mv = new ModelAndView("view");

        mv.addObject("board", board);
        mv.addObject("writer", nickname);
        log.info("Success find board {}", board);

        return mv;
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){

        log.info("Success delete id {}", id);

        boardService.delete(id);

        return "redirect:/board";
    }

    @GetMapping("/modify/{id}")
    public String modifyPage(Model model, @PathVariable("id") Long id){

        Board board = boardService.read(id);

        log.info("Success find for Modify {}", id);

        String nickname = boardService.read(id).getUser().getNickname();

        model.addAttribute("board", board);
        model.addAttribute("writer", nickname);

        return "modify";
    }

    @PutMapping("/modify")
    public String modify(BoardDto boardDto){

        log.info("Success ready for Modify {}", boardDto);

        boardService.update(boardDto);

        return "redirect:/board";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam(value="keyword") String keyword, @PageableDefault(sort = {"id"},
            direction = Sort.Direction.DESC,
            size = 3) Pageable pageable
    ){


        log.info("Success ready for search {}", keyword);

       Page<Board> boardList = boardService.searchBoard(keyword, pageable);

       Page<BoardDto> boardDtoList = boardService.toDtoList(boardList);

        int nowPage = boardDtoList.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, boardDtoList.getTotalPages());

        model.addAttribute("boards", boardDtoList);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "searchList";

    }

}
