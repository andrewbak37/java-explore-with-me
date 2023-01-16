package ru.yandex.practicum.statsservice.stat.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ViewStats {

    private final String app;
    private final String uri;
    private final Long hits;
}