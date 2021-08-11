package kr.co.home.dashboard.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberteamDuplicateException extends IllegalArgumentException {
    private static final String MESSAGE = "회원이 이미 선택한 팀입니다.";

    public MemberteamDuplicateException() {
        super(MESSAGE);
        log.error(MESSAGE);
    }
}
