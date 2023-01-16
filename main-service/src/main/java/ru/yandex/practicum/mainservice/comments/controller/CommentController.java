package ru.yandex.practicum.mainservice.comments.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.mainservice.comments.dto.CommentShortDto;
import ru.yandex.practicum.mainservice.comments.servise.CommentService;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{eventId}")
    public List<CommentShortDto> getComments(@PathVariable("eventId") Long eventId,
                                             @RequestParam("from") Integer from,
                                             @RequestParam("size") Integer size) {
        return commentService.getEventComments(eventId, from, size);
    }
}