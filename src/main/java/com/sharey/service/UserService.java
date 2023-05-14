package com.sharey.service;

import com.sharey.dto.UserDTO;
import com.sharey.entity.UserEntity;
import com.sharey.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 회원가입
    public void save(UserDTO userDTO) {
        // reqository의 save메소드 호출 ( entity객체를 넘겨줘야 함 )
        // 1. Dto -> entity 변환 toEntity() 메소드
        // 2. reqository의 save메소드 호출

        // UserEntity userEntity = UserEntity.toEntity(userDTO);
        UserEntity userEntity = UserEntity.builder().userDTO(userDTO).build();
        userRepository.save(userEntity);
    }
    // 로그인
    public UserEntity login(UserDTO userDTO) {
        // 1. 회원이 입력한 아이디를 db조회
        // 2. db에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 확인
        Optional<UserEntity> byId = userRepository.findByUserId(userDTO.getUserId());  // Entity객체를 Optional로 한번 더 감싼것임 로그인 유저가 입력한 id가 db에 있는지 확인 값이 있으면 전체 컬럼 값 리턴

        if(byId.isPresent()){ // 조회 결과가 있다.  id 존재 여부만 확인한 것이고, 비밀번호 대조는 아직 안한것임
            UserEntity userEntity = byId.get(); // Optional객체를 Entity객체화 하는 과정
            if(userEntity.getPassword().equals(userDTO.getPassword())) { // 입력받은 비밀번호와 db의 비밀번호 일치여부 확인
                // 비밀번호 일치
                return userEntity;
            }else{
                // 비밀번호 불일치
                return null;
            }
        }else{ // 조회 결과가 없다
            return null;
        }
    }
    // 회원목록 조회
    public List<UserEntity> findAll() {
        List<UserEntity> all = userRepository.findAll();  // repository 클래스의 리턴은 무조건 Entity타입
        List<UserEntity> userEntityList = new ArrayList<>();
        for (UserEntity userEntity : all) {
            userEntityList.add(userEntity);
        }
        return userEntityList;
    }

    // 회원 상세조회
    public UserDTO userDetail(String id) {
        Optional<UserEntity> byId = userRepository.findByUserId(id);
        if (byId.isPresent()){  // 조회 성공
            return UserDTO.toUserDTO(byId.get());
        }else{  // 조회 결과가 없다
            return null;
        }
    }

    // 회원정보 수정페이지 로딩 시 나오는 회원정보
    /*public UserEntity updateFoam(String id) {
        //Optional<UserEntity> byUserId = userRepository.findByUserId(id);
        UserEntity userEntity = UserEntity.builder().build();

        if(byUserId.isPresent()){
            userEntity = byUserId.get();
            return userEntity;
        }else{
            return null;
        }
    }*/

    // 회원정보 수정
    public void update(UserDTO userDTO, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("loginInfo");
        user.update(userDTO);
        userRepository.save(user);  // primary Key 값이 테이블에 있는 경우 알아서 Update쿼리를 실행함.
    }
}
