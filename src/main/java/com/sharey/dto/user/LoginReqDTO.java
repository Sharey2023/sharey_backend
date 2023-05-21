package com.sharey.dto.user;

import com.sharey.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginReqDTO {
    private String userId;
    private String password;
}
