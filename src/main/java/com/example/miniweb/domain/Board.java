package com.example.miniweb.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "user")
@Data
@Builder
@DynamicUpdate
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private int viewCount;

    @Column(updatable = false)
    private LocalDateTime registeredDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="userId")
    private User user;

}
