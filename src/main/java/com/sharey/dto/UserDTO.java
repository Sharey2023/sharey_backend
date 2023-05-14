package com.sharey.dto;

import com.sharey.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO {
    // private Long seq;
    private String userId, password, userName;
    private int tel, birthday;

    public static UserDTO toUserDTO(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();
       //  userDTO.setSeq(userEntity.getSeq());
        userDTO.setUserId(userEntity.getUserId());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setUserName(userEntity.getUserName());
        userDTO.setTel(userEntity.getTel());
        userDTO.setBirthday(userEntity.getBirthday());

        return userDTO;
    }
}
