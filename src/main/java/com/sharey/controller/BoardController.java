package com.sharey.controller;

import com.sharey.dto.board.*;
import com.sharey.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"게시판"})
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/sharey/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/insert")  // 게시글 작성
    @ApiOperation(value = "게시글 작성")
    public ResponseDTO<Boolean> insertBoard(@RequestBody InsertReqDTO dto){
        try {
            boardService.save(dto);
        }catch (NullPointerException e){
            e.printStackTrace();
            return new ResponseDTO<>(HttpStatus.OK, null);
        }
        return new ResponseDTO<>(HttpStatus.OK, true);
    }
    @GetMapping("/list")
    @ApiOperation(value = "게시글 목록 조회")
    public ResponseDTO<List> list(){
        List<ListRespDTO> list =  boardService.findAll();
        return new ResponseDTO<>(HttpStatus.OK, list);
    }
    @GetMapping("/detail/{seq}")
    @ApiOperation(value = "게시글 상세 조회")
    public ResponseDTO<DetailRespDTO> detail(@PathVariable Long seq){
        boardService.countUp(seq);  // 조회수 증가
        DetailRespDTO dto = boardService.detail(seq); // 상세 내용 가져오기
        return new ResponseDTO<>(HttpStatus.OK, dto);
    }
    @GetMapping("/update/{seq}")
    @ApiOperation(value = "게시글 수정폼에 들어갈 데이터")
    public ResponseDTO<DetailRespDTO> updateFoam(@PathVariable Long seq){
        DetailRespDTO dto = boardService.detail(seq);
        return new ResponseDTO<>(HttpStatus.OK, dto);
    }
    @PutMapping("/update")
    @ApiOperation(value = "게시글 수정")
    public ResponseDTO<Boolean> update(@RequestBody BoardUpdateReqDTO dto){
        try {
            boardService.update(dto);
            return new ResponseDTO<>(HttpStatus.OK, true);
        }catch (Exception e){
            e.printStackTrace();
            log.info("BoardController update : 게시글 수정 실패");
            return new ResponseDTO<>(HttpStatus.OK, false);
        }
    }
    @DeleteMapping("/delete/{seq}")
    @ApiOperation(value = "게시글 삭제")
    public ResponseDTO<Boolean> delete(@PathVariable Long seq){
        boardService.delete(seq);
        return new ResponseDTO<>(HttpStatus.OK, true);
    }
}
