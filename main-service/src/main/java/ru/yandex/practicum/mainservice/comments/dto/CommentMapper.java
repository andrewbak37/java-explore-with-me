package ru.yandex.practicum.mainservice.comments.dto;

import ru.yandex.practicum.mainservice.comments.model.Comment;
import ru.yandex.practicum.mainservice.event.dto.EventTinyDto;
import ru.yandex.practicum.mainservice.event.model.Event;
import ru.yandex.practicum.mainservice.user.dto.UserShortDto;
import ru.yandex.practicum.mainservice.user.model.User;

public class CommentMapper {

    public static Comment mapToComment(NewCommentDto newCommentDto, User user, Event event) {
        Comment comment = new Comment();
        comment.setOwner(user);
        comment.setEvent(event);
        comment.setDescription(newCommentDto.getText());

        return comment;
    }

    public static CommentShortDto mapToCommentShortDto(Comment comment) {
        return new CommentShortDto(
                comment.getId(),
                new UserShortDto(
                        comment.getOwner().getUserId(),
                        comment.getOwner().getName()
                ),
                comment.getDescription()
        );
    }

    public static CommentDto mapToCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                new UserShortDto(
                        comment.getOwner().getUserId(),
                        comment.getOwner().getName()
                ),
                new EventTinyDto(
                        comment.getEvent().getId(),
                        comment.getEvent().getTitle()
                ),
                comment.getDescription(),
                comment.getCreatedOn(),
                comment.getLastUpdate() != null
        );
    }
}