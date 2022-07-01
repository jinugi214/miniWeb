package com.example.miniweb.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "boards")
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    private String userId;

    private String password;

    @Column(updatable = false)
    private String name;

    private String email;

    private String nickname;

    private String role;

    private int point;

    @Column(updatable = false)
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    List<Board> boards = new ArrayList<>();

}