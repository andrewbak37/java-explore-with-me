package ru.yandex.practicum.client.clients;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ViewStats {

    private final String app;
    private final String uri;
    private final Long hits;
}
