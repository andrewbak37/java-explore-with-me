package ru.yandex.practicum.mainservice.event.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class EventSearchParams {

    private final String text;
    private final List<Long> users;
    private final List<EventState> states;
    private final List<Long> categories;
    private final Boolean paid;
    private final String rangeStart;
    private final String rangeEnd;
    private final Boolean onlyAvailable;
    private final String sort;
}
