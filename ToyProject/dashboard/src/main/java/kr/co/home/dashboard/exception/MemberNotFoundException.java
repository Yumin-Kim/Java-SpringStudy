package kr.co.home.dashboard.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberNotFoundException extends IllegalArgumentException {

    private static final String MESSAGE = "존재 하지 않는 회원 입니다.";

    public MemberNotFoundException() {
        super(MESSAGE);
        log.error(MESSAGE);
    }
}
