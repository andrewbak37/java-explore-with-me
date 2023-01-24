package ru.yandex.practicum.mainservice.comments.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.mainservice.event.dto.EventTinyDto;
import ru.yandex.practicum.mainservice.user.dto.UserShortDto;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class CommentDto {

    private final Long id;
    private final UserShortDto user;
    private final EventTinyDto event;
    private final String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdOn;

    private final Boolean updated;
}