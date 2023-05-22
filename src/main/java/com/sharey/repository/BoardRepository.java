package com.sharey.repository;

import com.sharey.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    BoardEntity findBySeq(Long seq);

    @Modifying
    @Query("update BoardEntity b set b.view = b.view + 1 where b.seq = :seq")
    int updateView(@Param("seq") Long seq);
}
