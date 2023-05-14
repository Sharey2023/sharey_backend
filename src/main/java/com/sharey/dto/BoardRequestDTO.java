package com.sharey.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
public class BoardRequestDTO {
    private Long boardSeq;
    private String title, area, type, content, writer_id;
    private LocalDateTime localDateTime;
}
