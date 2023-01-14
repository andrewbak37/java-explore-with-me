package ru.yandex.practicum.statsservice.stat;

import ru.yandex.practicum.statsservice.stat.model.EndpointHit;
import ru.yandex.practicum.statsservice.stat.model.View;
import ru.yandex.practicum.statsservice.stat.model.ViewStats;
import ru.yandex.practicum.statsservice.stat.model.ViewStatsInterface;

public class ViewMapper {

    public static View mapToView(EndpointHit endpointHit) {
        return View.builder()
                .app(endpointHit.getApp())
                .ip(endpointHit.getIp())
                .uri(endpointHit.getUri())
                .timestamp(endpointHit.getTimestamp())
                .build();
    }

    public static ViewStats mapToViewStats(ViewStatsInterface stats) {
        return new ViewStats(stats.getApp(), stats.getUri(), stats.getHits());
    }
}
