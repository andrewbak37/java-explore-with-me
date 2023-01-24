package ru.yandex.practicum.mainservice.comments.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.mainservice.comments.dto.CommentDto;
import ru.yandex.practicum.mainservice.comments.servise.CommentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/comments")
public class AdminCommentController {

    private final CommentService commentService;

    @GetMapping("/users/{userId}")
    public List<CommentDto> getComments(@PathVariable("userId") Long userId,
                                        @RequestParam("from") Integer from,
                                        @RequestParam("size") Integer size) {
        return commentService.getUserComments(userId, from, size);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable("commentId") Long commentId) {
        commentService.deleteComment(commentId);
    }
}