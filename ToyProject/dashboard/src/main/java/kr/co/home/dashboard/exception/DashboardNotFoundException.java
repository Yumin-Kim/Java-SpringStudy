package kr.co.home.dashboard.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DashboardNotFoundException extends IllegalArgumentException {

    private static final String MESSAGE = "존재 하지 않는 게시판 글 입니다.";

    public DashboardNotFoundException() {
        super(MESSAGE);
        log.error(MESSAGE);
    }
}
