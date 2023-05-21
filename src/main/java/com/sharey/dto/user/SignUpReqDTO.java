package com.sharey.dto.user;

import com.sharey.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
public class SignUpReqDTO {
    private String userId;
    private String password;
    private String userName;
    private String tel;
    private String birthday;
    private String nickname;

    public static UserEntity toEntity(SignUpReqDTO dto){
        return UserEntity.builder()
                .userId(dto.getUserId())
                .password(dto.getPassword())
                .userName(dto.getUserName())
                .tel(dto.getTel())
                .birthday(dto.getBirthday())
                .nickname(dto.getNickname())
                .build();
    }
}
