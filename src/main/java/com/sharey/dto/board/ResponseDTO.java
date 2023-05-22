package com.sharey.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDTO<T> {
    HttpStatus status;  // 참고용
    // Message message;   // 프런트에게 메시지 전달
    T result;  // 실제 데이터
}
