package com.sharey.entity;

import com.sharey.dto.user.SignUpReqDTO;
import com.sharey.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @Column
    private String birthday;

    @Column
    private String tel;

    @Column
    private String nickname;

/*
    @Builder
    public UserEntity(String userId, String password, String userName, String tel, String birthday) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.tel = tel;
        this.birthday = birthday;
    }
*/

/*    public Long update(UserDTO userDTO){
        this.userId = userDTO.getUserId();
        this.password = userDTO.getPassword();
        this.userName = userDTO.getUserName();
        this.tel = userDTO.getTel();
        this.birthday = userDTO.getBirthday();

        return this.seq;
    }*/
}
