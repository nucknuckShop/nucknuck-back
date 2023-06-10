package com.shop.nucknuckshop.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.CONFLICT;

@Getter
@AllArgsConstructor
public enum ErrorCases {

        NOTFOUND("404", NOT_FOUND, "찾을 수 없습니다"),
        INVALID_REQUEST("400", BAD_REQUEST, "잘못된 요청입니다"),
        DUPLICATE("404", CONFLICT, "이미 존재하는 아이디입니다"),
        NO_AUTHORITY("404", CONFLICT, "권한이 없습니다");

        private final String code;
        private final HttpStatus status;
        private final String message;
}
