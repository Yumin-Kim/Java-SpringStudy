package kr.co.home.dashboard.api;

import kr.co.home.dashboard.CustomCollectorValidator;
import kr.co.home.dashboard.dto.CommentForm;
import kr.co.home.dashboard.dto.CommentsForm;
import kr.co.home.dashboard.dto.Req;
import kr.co.home.dashboard.dto.Res;
import kr.co.home.dashboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentAPIController {

    private final CommentService commentService;
    private final CustomCollectorValidator customCollectorValidator;

    @PostMapping("/comment/{userId}/{dashboardId}")
    public Res createComment(
            @PathVariable("userId") Long userId,
            @PathVariable("dashboardId") Long dashboardId,
            @Valid CommentForm commentForm
    ) {
        Req.CommentDto commentDto = commentService.createComment(userId, dashboardId , commentForm);
        return Res.isOk(commentDto);
    }

    // /api/comments/1/1,2,3/ body{[],[]}
    @PostMapping("/comments/{userId}/{dashboardsId}")
    public Res createComments(
            @PathVariable("userId") Long userId,
            @PathVariable("dashboardsId") List<Long> dashboardsId,
            @Valid @RequestBody List<CommentForm> commentsform,
            BindingResult bindingResult
    ) throws BindException {
        customCollectorValidator.validate(commentsform,bindingResult);
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        List<Req.CommentDto> multiCommentsDto = commentService.createComments(userId, dashboardsId, commentsform);
        return Res.isOk(multiCommentsDto);
    }

    @GetMapping("/comment/{dashboardId}")
    public Res findComment(@PathVariable("dashboardId") Long dashboardId) {
        List<Req.CommentDto> commentDto = commentService.findComment(dashboardId);
        return Res.isOk(commentDto);
    }

    @PutMapping("/comment/{commentId}")
    public Res modifyComment(
            @PathVariable("commentId") Long commentId,
            @Valid CommentForm commentForm
    ){
        Req.CommentDto commentDto = commentService.modifyComment(commentId, commentForm);
        return Res.isOk(commentDto);
    }


}
