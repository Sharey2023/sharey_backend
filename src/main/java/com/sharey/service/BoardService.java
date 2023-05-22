package com.sharey.service;

import com.sharey.dto.board.DetailRespDTO;
import com.sharey.dto.board.ListRespDTO;
import com.sharey.dto.board.InsertReqDTO;
import com.sharey.dto.board.BoardUpdateReqDTO;
import com.sharey.entity.BoardEntity;
import com.sharey.entity.UserEntity;
import com.sharey.repository.BoardRepository;
import com.sharey.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    // 게시글 작성
    @Transactional
    public void save(InsertReqDTO dto){
        UserEntity userEntity =  userRepository.findByNickname(dto.getNickname());  // 해당 닉네임을 가진 User의 정보를 가져옴.
        if(userEntity != null){
            dto.setWriter(userEntity);
            boardRepository.save(dto.toEntity(dto));
            log.info("BoardController save : 게시글 저장 성공");
        }else{
            throw new NullPointerException("작성자 아이디 조회 실패");
        }
    }
    // 게시글 목록 조회
    public List<ListRespDTO> findAll() {
        List<BoardEntity> all = boardRepository.findAll();
        List<ListRespDTO> list = new ArrayList<>();
        for(BoardEntity entity : all){
            String nick = entity.getUsers_seq().getNickname();  // 작성자 seq값을 닉네임으로 변경
            list.add(new ListRespDTO(entity, nick));
        }
        log.info("BoardController findAll : 게시글 목록 조회 완료");
        return list;
    }
    // 게시글 상세 조회 전 조회수 증가
    @Transactional
    public  void countUp(Long seq){
        boardRepository.updateView(seq);
        log.info("BoardService countUp : 조회수 1 증가");
    }
    // 게시글 상세 조회
    public DetailRespDTO detail(Long seq){
        BoardEntity bySeq = boardRepository.findBySeq(seq);
        String nick = bySeq.getUsers_seq().getNickname();   // 작성자 seq값을 닉네임으로 변경
        DetailRespDTO dto = new DetailRespDTO(bySeq, nick);
        log.info("BoardController detail : 게시글 상세 조회 완료");
        return dto;
    }
    // 게시글 수정
    @Transactional
    public void update(BoardUpdateReqDTO dto){
        UserEntity userEntity =  userRepository.findByNickname(dto.getNickname());  // 해당 닉네임을 가진 User의 정보를 가져옴.
        dto.setWriter(userEntity);
        boardRepository.save(dto.toEntity(dto));
    }
    // 게시글 삭제
    @Transactional
    public void delete(Long seq){
        boardRepository.deleteById(seq);
    }
}
