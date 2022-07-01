package com.example.miniweb.dto;

import com.example.miniweb.domain.Board;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
@Builder
public class UserDto {

    private String userId;

    private String password;

    private String name;

    private String email;

    private String nickname;

    private String role;

    private int point;

    private LocalDateTime createdDate;


}
