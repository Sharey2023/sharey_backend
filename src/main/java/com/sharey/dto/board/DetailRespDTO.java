package com.sharey.dto.board;

import com.sharey.entity.BoardEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class DetailRespDTO {
    private Long seq;
    private String title;
    private String content;
    private String nickname;
    private LocalDate date;
    private int view;

    public DetailRespDTO(BoardEntity entity, String nickname){
        this.seq = entity.getSeq();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.nickname = nickname;
        this.date = entity.getDate();
        this.view = entity.getView();
    }
}
