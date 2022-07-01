package com.example.miniweb.dto;

import com.example.miniweb.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
@Getter
@ToString
@Builder
public class BoardDto {

    private Long id;

    private String title;

    private String content;

    private String writer;

    private int viewCount;

    private LocalDateTime registeredDate;

    private Long userId;

}
