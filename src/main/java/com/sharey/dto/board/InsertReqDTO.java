package com.sharey.dto.board;

import com.sharey.entity.BoardEntity;
import com.sharey.entity.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class InsertReqDTO {
    private String title;
    private String content;
    private UserEntity writer;
    private LocalDate date;
    private int view;
    private String nickname;

    public static BoardEntity toEntity(InsertReqDTO dto){
        return BoardEntity.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .users_seq(dto.getWriter())
                .date(LocalDate.now())
                .view(0)
                .build();
    }
}
