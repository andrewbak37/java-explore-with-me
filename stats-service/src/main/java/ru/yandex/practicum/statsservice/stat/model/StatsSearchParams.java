package ru.yandex.practicum.statsservice.stat.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class StatsSearchParams {

    private final String start;
    private final String end;
    private final List<String> uris;
    private final Boolean unique;
}