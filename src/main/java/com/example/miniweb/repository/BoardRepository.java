package com.example.miniweb.repository;

import com.example.miniweb.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    @Modifying
    @Query("update Board b set b.viewCount = b.viewCount +1 where b.id = :id")
    void updateViewCount(Long id);

    Page<Board> findByTitleContaining(String keyword, Pageable pageable);

}
