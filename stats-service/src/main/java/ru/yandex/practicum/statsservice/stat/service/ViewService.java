package ru.yandex.practicum.statsservice.stat.service;

import ru.yandex.practicum.statsservice.stat.model.EndpointHit;
import ru.yandex.practicum.statsservice.stat.model.StatsSearchParams;
import ru.yandex.practicum.statsservice.stat.model.ViewStats;

import java.util.List;

public interface ViewService {

    void postStat(EndpointHit endpointHit);

    List<ViewStats> getStats(StatsSearchParams searchParams);
}
