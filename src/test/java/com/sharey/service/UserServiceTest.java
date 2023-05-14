package com.sharey.service;

import com.sharey.dto.UserDTO;
import com.sharey.entity.UserEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void 정보수정(){
        // 가입되어있는 회원
        UserDTO dto = new UserDTO();
        dto.setUserId("asdf");
        dto.setPassword("1234");
        dto.setUserName("asdf");
        dto.setTel(1234);
        dto.setBirthday(12345678);

        UserEntity user = UserEntity.builder()
                .userDTO(dto)
                .build();

        UserDTO update = new UserDTO();
        update.setUserId("asdf");
        update.setPassword("1234");
        update.setUserName("asdf");
        update.setTel(1234);
        update.setBirthday(12345678);

        Long updateSeq = user.update(update);

        Assertions.assertThat(user.getSeq()).isEqualTo(updateSeq);
    }
}