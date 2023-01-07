package ru.yandex.practicum.client.clients;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ViewStats {

    private final String app;
    private final String iri;
    private final Long hits;
}
