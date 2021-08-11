package kr.co.home.dashboard.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TeamNotFoundException extends IllegalArgumentException {
    private final static String MESSAGE = "팀이 존재 하지 않습니다";

    public TeamNotFoundException() {
        super(MESSAGE);
        log.error(MESSAGE);
    }
}
