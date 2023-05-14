package com.sharey.controller;

import com.sharey.dto.UserDTO;
import com.sharey.entity.UserEntity;
import com.sharey.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor    // 사용하는 객체에 맞는 생성자 생성
@Log4j2
public class UserController {
    private final UserService userService;
    // 생성자 주입 사용

    // --- 회원가입 및 로그인 관련
    // Request SignUp Page
    @GetMapping("/user/signUp")
    public String signUpForm(){
        return "user/signUp";
    }
    // 회원가입 페이지에서 submit 했을 때 요청 처리
    @PostMapping("/signUp.do")
    public String singUp(@ModelAttribute UserDTO userDTO){    // RequestParam은 폼태그에서 넘어온 name값을 각각 가져옴 @RequestParam("id") @ModelAttribute는 Foam태그의 값을 한번에 받음
        userService.save(userDTO);
        return "user/login";
    }
    // Request Loing
    @GetMapping("/user/login")
    public String loginForm(){
        return "user/login";
    }

    @PostMapping("/login.do")
    public String login(@ModelAttribute UserDTO userDTO, HttpSession session){
        UserEntity result = userService.login(userDTO);
        if(result != null) {
            log.info("userController login : 로그인성공 로그인id : " + result.getUserId());
            session.setAttribute("loginInfo", result);
            return "user/main";
        }
        else {
            log.info("userController login : 로그인실패");
            return "user/login";
        }
    }

    // 회원목록 조회
    @GetMapping("/user/list")
    public String list(Model model){
        List<UserEntity> list = userService.findAll();
        // html로 가져갈 데이터가 있다면 model사용
        model.addAttribute("userList", list);
        return "user/list";
    }

    // 회원상세 조회
    @GetMapping("/user/{id}")
    public String findById(@PathVariable String id, Model model){  // rest API 방식을 이용할 때 즉 경로상의 값을 가져올 때 PathVariable사용
        UserDTO userDTO = userService.userDetail(id);
        model.addAttribute("user", userDTO);
        return "user/detail";
    }

    // 회원정보 수정폼 요청
    @GetMapping("/user/update")
    public String updateFoam(HttpSession session, Model model){
        UserEntity info = (UserEntity)session.getAttribute("loginInfo");  // 현재 로그인한 사용자의 정보를 세션에서 가져옴.
        //UserEntity userEntity = userService.updateFoam(id);
        model.addAttribute("userInfo", info);  // 가저온 세션정보를 View엑서 보여주기 위해 add.
        return "user/update";
    }
    // 회원정보 수정
    @PostMapping("/user/update.do")
    public String updateUser(@ModelAttribute UserDTO userDTO, HttpSession session){
        userService.update(userDTO, session);
        log.info("userController updateUser Sucess");
        return "redirect:/user/" + userDTO.getUserId();
    }
    // 회원 삭제
    @GetMapping("/user/delete/{seq}")
    public String deleteUser(@PathVariable Long seq){
        userService.deleteUser(seq);

        return "redirect:/user/list";
    }
}