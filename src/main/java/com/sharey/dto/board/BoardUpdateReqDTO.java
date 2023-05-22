package com.sharey.dto.board;

import com.sharey.entity.BoardEntity;
import com.sharey.entity.UserEntity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BoardUpdateReqDTO {
    private Long seq;
    private String title;
    private String content;
    private UserEntity writer;
    private LocalDate date;
    private int view;
    private String nickname;


    public static BoardEntity toEntity(BoardUpdateReqDTO dto){
        return BoardEntity.builder()
                .seq(dto.getSeq())
                .title(dto.getTitle())
                .content(dto.getContent())
                .users_seq(dto.getWriter())
                .date(dto.getDate())
                .view(dto.getView())
                .build();
    }
    public static BoardEntity toEntityUp(BoardUpdateReqDTO dto){
        return BoardEntity.builder()
                .seq(dto.getSeq())
                .title(dto.getTitle())
                .content(dto.getContent())
                .users_seq(dto.getWriter())
                .date(dto.getDate())
                .view(dto.getView()+1)
                .build();
    }
}
