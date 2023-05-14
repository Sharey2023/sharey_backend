package com.sharey.service;

import com.sharey.dto.UserDTO;
import com.sharey.entity.UserEntity;
import com.sharey.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;


    @Test
    public void 회원가입(){
        UserDTO dto = new UserDTO();
        dto.setUserId("test1");
        dto.setPassword("1234");
        dto.setUserName("test1");
        dto.setBirthday(19990101);
        dto.setTel(010);

        UserEntity userEntity = UserEntity.builder().userDTO(dto).build();
        // userRepository.save(userEntity);  db에 삽입할 경우 주석을 해제하고 사용
    }

    @Test
    public void 회원목록(){
        List<UserEntity> all = userRepository.findAll();
        List<UserEntity> userList = new ArrayList<>();
        // 회원 목록을 리스트에 담음
        for (UserEntity userEntity: all) {
            userList.add(userEntity);
        }
        // 리스트에 있는 목록 출력
        for(UserEntity user:userList){
            System.out.println(user.getSeq() +", "+  user.getUserId() +", "+ user.getPassword() +", "+ user.getUserName() );
        }
    }

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

        //update test case 추가
    }
    @Test
    public void 회원삭제(){

        // userRepository.deleteById( 삭제할 기본키 입력 );
    }
}