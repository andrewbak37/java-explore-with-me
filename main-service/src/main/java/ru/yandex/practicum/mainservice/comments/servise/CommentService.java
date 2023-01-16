package ru.yandex.practicum.mainservice.comments.servise;

import ru.yandex.practicum.mainservice.comments.dto.CommentDto;
import ru.yandex.practicum.mainservice.comments.dto.CommentShortDto;
import ru.yandex.practicum.mainservice.comments.dto.NewCommentDto;

import java.util.List;

public interface CommentService {
    List<CommentShortDto> getEventComments(Long eventId, Integer from, Integer size);

    List<CommentDto> getUserComments(Long userId, Integer from, Integer size);

    CommentDto addComment(Long userId, Long eventId, NewCommentDto newCommentDto);

    CommentDto updateComment(Long userId, Long commentId, NewCommentDto newCommentDto);

    void deleteComment(Long commentId);
}
