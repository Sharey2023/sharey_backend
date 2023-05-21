package com.sharey.dto.user;

import com.sharey.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRespDTO {

    private Long seq;
    private String userId;
    private String password;
    private String userName;
    private String tel;
    private String birthday;
    private String email;
    private String nickname;

    public UserRespDTO(UserEntity entity){
        this.seq = entity.getSeq();;
        this.userId = entity.getUserId();
        this.password = entity.getPassword();
        this.userName = entity.getUserName();
        this.tel = entity.getTel();
        this.birthday = entity.getBirthday();
        this.email = entity.getEmail();
        this.nickname = entity.getNickname();
    }
}
