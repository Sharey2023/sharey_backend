package com.sharey.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "boards")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardSeq;

    private String title, area, type, content, writer_id;
    private int view_cnt;
    private LocalDateTime localDateTime;
}
