package kr.co.home.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class CommentForm {
    @NotBlank(message = "댓글 내용을 확인해주세요")
    @NotNull(message = "댓글 내용이 존재하지 않습니다")
    private String content;

}
