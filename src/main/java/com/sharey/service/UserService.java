package com.sharey.service;

import com.sharey.dto.user.*;
import com.sharey.entity.UserEntity;
import com.sharey.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    // 회원가입
    public void save(SignUpReqDTO dto) {
        // reqository의 save메소드 호출 ( entity객체를 넘겨줘야 함 )
        // 1. Dto -> entity 변환 toEntity() 메소드
        // 2. reqository의 save메소드 호출
        UserEntity entity = dto.toEntity(dto);

        userRepository.save(entity);
        log.info("userService save : 회원가입 로직 실행");
    }
    // 아이디 중복 확인
    public Boolean checkId(String id){
        Optional<UserEntity> byUserId = userRepository.findByUserId(id);
        if(byUserId.isPresent()){ // 아이디가 있을 경우
            log.info("UserService checkId : 아이디 중복 ");
            return null;
        }
        return true;
    }
    // 닉네임 중복 확인
    public Boolean checkNick(String nickname){
        UserEntity byNickname = userRepository.findByNickname(nickname);
        if(byNickname != null){ // 닉네임이 있을 경우
            log.info("UserService checkNick : 닉네임 중복 ");
            return null;
        }
        return true;
    }
    // 로그인
    public UserRespDTO login(LoginReqDTO dto) {
        // 1. 회원이 입력한 아이디를 db조회
        // 2. db에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 확인
        Optional<UserEntity> byId = userRepository.findByUserId(dto.getUserId());  // Entity객체를 Optional로 한번 더 감싼것임 로그인 유저가 입력한 id가 db에 있는지 확인 값이 있으면 전체 컬럼 값 리턴

        if(byId.isPresent()){ // 조회 결과가 있다.  id 존재 여부만 확인한 것이고, 비밀번호 대조는 아직 안한것임
            UserEntity userEntity = byId.get(); // Optional객체를 Entity객체화 하는 과정
            if(userEntity.getPassword().equals(dto.getPassword())) { // 입력받은 비밀번호와 db의 비밀번호 일치여부 확인
                // 비밀번호 일치
                return new UserRespDTO(userEntity);
            }else{
                // 비밀번호 불일치
                log.info("userService login : 비밀번호 불일치.");
                return null;
            }
        }else{ // 조회 결과가 없다
            log.info("userService login : 조회 결과가 없음.");
            return null;
        }
    }
    // 회원목록 조회
    public List<UserRespDTO> findAll() {
        List<UserEntity> all = userRepository.findAll();  // repository 클래스의 리턴은 무조건 Entity타입
        List<UserRespDTO> userRespDTOList = new ArrayList<>();
        for (UserEntity userEntity : all) {
            userRespDTOList.add(new UserRespDTO(userEntity));
        }
        return userRespDTOList;
    }

    // 회원 상세조회( 회원정보 수정창에서도 사용 )
    public UserRespDTO userDetail(String id) {
        Optional<UserEntity> byId = userRepository.findByUserId(id);   // 회원 Id를 기준으로 db조회
        if (byId.isPresent()){  // 조회 성공
            return new UserRespDTO(byId.get());
        }else{  // 조회 결과가 없다
            log.info("UserService userDetail is null");
            return null;
        }
    }

    // 회원정보 수정
    public void update(UpdateReqDTO dto) {
        UserEntity entity = dto.toEntity(dto);
        userRepository.save(entity);  // primary Key 값이 테이블에 있는 경우 알아서 Update쿼리를 실행함.
    }

    // 회원 삭제
    public void deleteUser(Long seq) {
        userRepository.deleteById(seq);
        log.info("userService deleteUser : 회원 삭제 완료");
    }
}
