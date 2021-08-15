package kr.co.home.dashboard.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommentNotFoundException extends IllegalArgumentException {

    private static final String MESSAGE = "존재 하지 않는 댓글 입니다.";

    public CommentNotFoundException() {
        super(MESSAGE);
        log.error(MESSAGE);
    }
}
