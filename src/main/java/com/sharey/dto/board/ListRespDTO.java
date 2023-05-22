package com.sharey.dto.board;

import com.sharey.entity.BoardEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ListRespDTO {
    private Long seq;
    private String title;
    private String nickname;
    private LocalDate date;
    private int view;

    public ListRespDTO(BoardEntity entity, String nickname){
        this.seq = entity.getSeq();
        this.title = entity.getTitle();
        this.nickname = nickname;
        this.date = entity.getDate();
        this.view = entity.getView();
    }
}
