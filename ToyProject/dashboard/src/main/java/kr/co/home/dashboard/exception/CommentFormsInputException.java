package kr.co.home.dashboard.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommentFormsInputException extends IllegalArgumentException {

    private static final String MESSAGE = "입력한 form 정보 수량과 게시글 수량이 맞지 않습니다.";

    public CommentFormsInputException() {
        super(MESSAGE);
        log.error(MESSAGE);
    }

}
