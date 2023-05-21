package com.sharey.dto.user;

import com.sharey.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateReqDTO {
    private Long seq;
    private String userId;
    private String password;
    private String userName;
    private String tel;
    private String birthday;
    private String email;
    private String nickname;

    public static UserEntity toEntity(UpdateReqDTO dto){
        return UserEntity.builder()
                .seq(dto.getSeq())
                .userId(dto.getUserId())
                .password(dto.getPassword())
                .userName(dto.getUserName())
                .tel(dto.getTel())
                .birthday(dto.getBirthday())
                .email(dto.getEmail())
                .nickname(dto.getNickname())
                .build();
    }
}
