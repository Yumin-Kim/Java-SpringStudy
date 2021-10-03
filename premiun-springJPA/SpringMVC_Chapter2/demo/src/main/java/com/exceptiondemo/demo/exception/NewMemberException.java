package com.exceptiondemo.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "잘못된 요청입니다.")
public class NewMemberException  extends RuntimeException{
}
