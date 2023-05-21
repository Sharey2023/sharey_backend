package com.sharey.controller;

import com.sharey.dto.user.*;
import com.sharey.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api (tags = {"회원 관련"},description = "회원 관련")
@RestController
@RequiredArgsConstructor    // 사용하는 객체에 맞는 생성자 생성
@Log4j2
@RequestMapping("/sharey/users")
public class SwaggerUserController {
    private final UserService userService;
    // 생성자 주입 사용
    // --- 회원가입 및 로그인 관련

    // 회원가입 페이지에서 submit 했을 때 요청 처리
    @PostMapping("/signUp")
    @ApiOperation(value = "회원가입 로직")
    public ResponseDTO<Boolean> singUp(@RequestBody SignUpReqDTO dto){    // RequestParam은 폼태그에서 넘어온 name값을 각각 가져옴 @RequestParam("id") @ModelAttribute는 Foam태그의 값을 한번에 받음
         userService.save(dto);
         return new ResponseDTO<>(HttpStatus.OK, true);
    }
    @GetMapping("/checkId/{id}")  // 아이디 중복 확인
    @ApiOperation(value = "아이디 중복 확인")
    public ResponseDTO<Boolean> checkId(@PathVariable String id){
        Boolean result;
        try{
            result = userService.checkId(id);
        }catch (NullPointerException e){
            e.printStackTrace();
            log.info("UserController checkId : 아이디 중복");
            return new ResponseDTO<>(HttpStatus.OK, null);
        }
        return new ResponseDTO<>(HttpStatus.OK, result);
    }
    @GetMapping("/checkNick/{nickname}")  // 닉네임 중복 확인
    @ApiOperation(value = "닉네임 중복 확인")
    public ResponseDTO<Boolean> checkNick(@PathVariable  String nickname){
        Boolean result;
        try{
            result = userService.checkNick(nickname);
        }catch (NullPointerException e){
            e.printStackTrace();
            log.info("UserController checkNick : 아이디 중복");
            return new ResponseDTO<>(HttpStatus.OK, null);
        }
        return new ResponseDTO<>(HttpStatus.OK, result);
    }
    @PostMapping("/login")
    @ApiOperation(value = "로그인 로직")
    public ResponseDTO<UserRespDTO> login(@RequestBody LoginReqDTO dto){
        UserRespDTO result;
        try {
            result = userService.login(dto);
        } catch (NullPointerException e){
            e.printStackTrace();
            log.info("UserController login : 로그인 실패");
            return new ResponseDTO<>(HttpStatus.OK, null);
        }
        return new ResponseDTO<>(HttpStatus.OK, result);
    }

    // 회원목록 조회
    @GetMapping("/list")
    @ApiOperation(value = "회원 목록리스트")
    public ResponseDTO<List> list(){
        List<UserRespDTO> list = userService.findAll();
        return new ResponseDTO<>(HttpStatus.OK, list);
    }

    // 회원상세 조회
    @GetMapping("/{id}")
    @ApiOperation(value = "회원 상세조회")
    public ResponseDTO<UserRespDTO> userDetail(@PathVariable String id){  // rest API 방식을 이용할 때 즉 경로상의 값을 가져올 때 PathVariable사용
        UserRespDTO dto = userService.userDetail(id);
        return new ResponseDTO<>(HttpStatus.OK, dto);
    }

    // 회원정보 수정
    // 회원정보 수정폼에 들어갈 데이터 요청
    @GetMapping("/update/{id}")
    public ResponseDTO<UserRespDTO> editDetail(@PathVariable String id){  // rest API 방식을 이용할 때 즉 경로상의 값을 가져올 때 PathVariable사용
        UserRespDTO dto = userService.userDetail(id);
        return new ResponseDTO<>(HttpStatus.OK, dto);
    }
    // 회원정보 수정 요청
    @PutMapping("/update")
    @ApiOperation(value = "회원 정보수정")
    public ResponseDTO<Boolean> updateUser(@RequestBody UpdateReqDTO dto){
        userService.update(dto);
        log.info("userController updateUser Sucess");
        return new ResponseDTO<>(HttpStatus.OK, true);
    }
    // 회원 삭제
    @DeleteMapping("/delete/{seq}")
    @ApiOperation(value = "회원 삭제")
    public ResponseDTO<Boolean> deleteUser(@PathVariable Long seq){
        userService.deleteUser(seq);
        return new ResponseDTO<>(HttpStatus.OK, true);
    }
}