package com.sharey.entity;

import com.sharey.dto.UserDTO;
import lombok.Getter;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Getter
@Table(name = "users")
public class UserEntity {
    // html파일의 name과 잂치해야 함.

    @Id  // pk를 뜻함
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // AI를 지정해주는 것
    private Long seq;

    @Column(unique = true, length = 10)
    private String userId;

    @Column(nullable = false, length = 20)
    private String password;

    @Column
    private String userName;

    @Column(length = 8)
    private int birthday;

    @Column
    private int tel;

    public static UserEntity toEntity(UserDTO userDTO){   // DTO -> Entity
        UserEntity userEntity = new UserEntity();
        userEntity.userId = userDTO.getUserId();
        userEntity.password = userDTO.getPassword();
        userEntity.userName = userDTO.getUserName();
        userEntity.birthday = userDTO.getBirthday();
        userEntity.tel = userDTO.getTel();
        return userEntity;
    }
    public static UserEntity toUpdateEntity(UserDTO userDTO){   // DTO -> Entity
        UserEntity userEntity = new UserEntity();
        userEntity.seq = userDTO.getSeq();
        userEntity.userId = userDTO.getUserId();
        userEntity.password = userDTO.getPassword();
        userEntity.userName = userDTO.getUserName();
        userEntity.birthday = userDTO.getBirthday();
        userEntity.tel = userDTO.getTel();
        return userEntity;
    }
}
