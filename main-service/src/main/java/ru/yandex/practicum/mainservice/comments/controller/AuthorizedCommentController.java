package ru.yandex.practicum.mainservice.comments.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.mainservice.comments.dto.CommentDto;
import ru.yandex.practicum.mainservice.comments.dto.NewCommentDto;
import ru.yandex.practicum.mainservice.comments.servise.CommentService;

import javax.validation.Valid;

@RestController
@RequestMapping("/users/{userId}/comments")
@RequiredArgsConstructor
public class AuthorizedCommentController {

    private final CommentService commentService;

    @PostMapping("/{eventId}")
    public CommentDto addComment(@PathVariable("userId") Long userId,
                                 @PathVariable("eventId") Long eventId,
                                 @Valid @RequestBody NewCommentDto newCommentDto) {
        return commentService.addComment(userId, eventId, newCommentDto);
    }

    @PatchMapping("/{commentId}")
    public CommentDto updateComment(@PathVariable("userId") Long userId,
                                    @PathVariable("commentId") Long commentId,
                                    @Valid @RequestBody NewCommentDto newCommentDto) {

        return commentService.updateComment(userId, commentId, newCommentDto);
    }
}