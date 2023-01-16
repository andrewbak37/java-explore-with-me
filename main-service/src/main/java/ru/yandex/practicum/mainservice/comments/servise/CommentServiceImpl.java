package ru.yandex.practicum.mainservice.comments.servise;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.mainservice.comments.dto.CommentDto;
import ru.yandex.practicum.mainservice.comments.dto.CommentMapper;
import ru.yandex.practicum.mainservice.comments.dto.CommentShortDto;
import ru.yandex.practicum.mainservice.comments.dto.NewCommentDto;
import ru.yandex.practicum.mainservice.comments.model.Comment;
import ru.yandex.practicum.mainservice.comments.repository.CommentRepository;
import ru.yandex.practicum.mainservice.event.model.Event;
import ru.yandex.practicum.mainservice.event.model.EventState;
import ru.yandex.practicum.mainservice.event.repository.EventRepository;
import ru.yandex.practicum.mainservice.exception.ForbiddenException;
import ru.yandex.practicum.mainservice.exception.ObjectNotFoundException;
import ru.yandex.practicum.mainservice.exception.ValidationException;
import ru.yandex.practicum.mainservice.user.model.User;
import ru.yandex.practicum.mainservice.user.repository.UserRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final CommentRepository commentRepository;

    @Override
    public List<CommentShortDto> getEventComments(Long eventId, Integer from, Integer size) {
        eventRepository.findById(eventId).orElseThrow(()
                -> new ObjectNotFoundException("Event not found"));
        Pageable pageable = PageRequest.of(from / size, size);
        return commentRepository.findCommentsByEventId(eventId, pageable)
                .stream()
                .map(CommentMapper::mapToCommentShortDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getUserComments(Long userId, Integer from, Integer size) {
        userRepository.findById(userId).orElseThrow(()
                -> new ObjectNotFoundException("User not found"));
        Pageable pageable = PageRequest.of(from / size, size);
        return commentRepository.findCommentsByOwner_UserId(userId, pageable)
                .stream()
                .map(CommentMapper::mapToCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto addComment(Long userId, Long eventId, NewCommentDto newCommentDto) {
        User user = userRepository.findById(userId).orElseThrow(()
                -> new ObjectNotFoundException("User not found"));
        Event event = eventRepository.findById(eventId).orElseThrow(()
                -> new ObjectNotFoundException("Event not found"));
        if (!event.getState().equals(EventState.PUBLISHED)) {
            throw new ForbiddenException("Access denied");
        }
        Comment comment = CommentMapper.mapToComment(newCommentDto, user, event);
        return CommentMapper.mapToCommentDto(commentRepository.save(comment));
    }

    @Override
    public CommentDto updateComment(Long userId, Long commentId, NewCommentDto newCommentDto) {
        userRepository.findById(userId).orElseThrow(()
                -> new ObjectNotFoundException("User not found"));
        Comment comment = commentRepository.findById(commentId).orElseThrow(()
                -> new ObjectNotFoundException("Comment not found"));
        if (!comment.getOwner().getUserId().equals(userId)) {
            throw new ForbiddenException("Access denied");
        }
        if (Duration.between(comment.getCreatedOn(), LocalDateTime.now()).toHours() > 24) {
            throw new ValidationException("Validation failed");
        }
        fillComment(comment, newCommentDto);
        return CommentMapper.mapToCommentDto(commentRepository.save(comment));
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()
                -> new ObjectNotFoundException("Comment not found"));
        commentRepository.save(comment);
    }

    private void fillComment(Comment comment, NewCommentDto newCommentDto) {
        comment.setLastUpdate(LocalDateTime.now());
        comment.setDescription(newCommentDto.getText());
    }
}