package ru.yandex.practicum.statsservice.stat;

import ru.yandex.practicum.statsservice.stat.model.EndpointHit;
import ru.yandex.practicum.statsservice.stat.model.View;
import ru.yandex.practicum.statsservice.stat.model.ViewStats;

public class ViewMapper {

    public static View mapToView(EndpointHit endpointHit) {
        return View.builder()
                .app(endpointHit.getApp())
                .ip(endpointHit.getIp())
                .uri(endpointHit.getUri())
                .timestamp(endpointHit.getTimestamp())
                .build();
    }

    public static ViewStats mapToViewStats(View view, Long countView) {
        return new ViewStats(
                view.getApp(),
                view.getUri(),
                countView);

    }
}
