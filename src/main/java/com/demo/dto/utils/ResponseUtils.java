package com.demo.dto.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@UtilityClass
public class ResponseUtils {

    public ResponseEntity<Response> badRequest(ErrorResponse error) {
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Response> internalErr(ErrorResponse error) {
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<Response> notSuccess(HttpStatus httpStt, ErrorResponse error) {
        return new ResponseEntity<>(error, httpStt);
    }

    public ResponseEntity<Response> ok() {
        return ResponseEntity.ok(Response.of(StringUtils.EMPTY, null));
    }

    public ResponseEntity<Response> ok(String msg) {
        return ResponseEntity.ok(Response.of(msg, null));
    }

    public ResponseEntity<Response> ok(Object data) {
        return ResponseEntity.ok(Response.of(StringUtils.EMPTY, data));
    }

    public ResponseEntity<Response> ok(Page<?> page) {
        return ResponseEntity.ok(PagingRes.of(page));
    }

    public ResponseEntity<Response> ok(String msg, Object data) {
        return ResponseEntity.ok(Response.of(msg, data));
    }

}
