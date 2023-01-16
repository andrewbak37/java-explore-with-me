package ru.yandex.practicum.mainservice.comments.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.mainservice.user.dto.UserShortDto;

@Getter
@RequiredArgsConstructor
public class CommentShortDto {

    private final Long id;
    private final UserShortDto user;
    private final String description;
}