package ru.yandex.practicum.mainservice.event.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class EventTinyDto {

    private final Long id;
    private final String title;
}