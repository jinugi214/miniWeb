package com.example.miniweb.service;

import com.example.miniweb.domain.Board;
import com.example.miniweb.dto.BoardDto;
import com.example.miniweb.repository.BoardRepository;
import com.example.miniweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    private final UserRepository userRepository;

    @Transactional
    public void write(BoardDto boardDto){

        Board board = Board.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .viewCount(boardDto.getViewCount())
                .registeredDate(LocalDateTime.now())
                .user(userRepository.findByNickname(boardDto.getWriter()))
                .build();

        boardRepository.save(board);
    }


    public Board read(Long id){

        return boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("없는 글입니다."));

    }

    public void delete(Long id){

        boardRepository.deleteById(id);
    }

    @Transactional
    public void update(BoardDto boardDto){

        Board board = boardRepository.findById(boardDto.getId())
                .orElseThrow(() -> new RuntimeException("없는 글입니다."));

        board = Board.builder()
                .id(boardDto.getId())
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .user(userRepository.findByNickname(boardDto.getWriter()))
                .build();

        boardRepository.save(board);
    }


    @Transactional(readOnly = true)
    public Page<Board> readAllBoard(Pageable pageable){

        return boardRepository.findAll(pageable);
    }

    public Page<Board> searchBoard(String keyword, Pageable pageable){
        Page<Board> boardList = boardRepository.findByTitleContaining(keyword, pageable);
        return boardList;
    }


    public Page<BoardDto> toDtoList(Page<Board> boardList){
        Page<BoardDto> boardDtoList = boardList.map(m ->
                        BoardDto.builder()
                                .id(m.getId())
                                .title(m.getTitle())
                                .content(m.getContent())
                                .writer(m.getUser().getNickname())
                                .viewCount(m.getViewCount())
                                .registeredDate(m.getRegisteredDate())
                                .userId(m.getUser().getId())
                                .build());
                return boardDtoList;
    }

    @Transactional
    public void updateViewCount(Long boardId){
        boardRepository.updateViewCount(boardId);
    }


}
