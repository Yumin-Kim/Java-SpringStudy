package kr.co.home.dashboard.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberFoundException extends IllegalArgumentException{
    private static final String MESSAGE = "존재하는 아이디 입니다.";

    public MemberFoundException() {
        super(MESSAGE);
        log.error(MESSAGE);
    }
}
