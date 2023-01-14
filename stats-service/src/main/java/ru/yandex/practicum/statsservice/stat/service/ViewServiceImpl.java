package ru.yandex.practicum.statsservice.stat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.statsservice.stat.ViewMapper;
import ru.yandex.practicum.statsservice.stat.model.EndpointHit;
import ru.yandex.practicum.statsservice.stat.model.StatsSearchParams;
import ru.yandex.practicum.statsservice.stat.model.View;
import ru.yandex.practicum.statsservice.stat.model.ViewStats;
import ru.yandex.practicum.statsservice.stat.repository.ViewRepository;
import java.util.List;
import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class ViewServiceImpl implements ViewService {

    private final ViewRepository viewRepository;

    @Override
    public void postStat(EndpointHit endpointHit) {
        View view = ViewMapper.mapToView(endpointHit);
        viewRepository.save(view);
    }

    @Override
    public List<ViewStats> getStats(StatsSearchParams searchParams) {
        List<Long> viewIds = viewRepository.searchStatsByParams(searchParams)
                .stream()
                .map(View::getId)
                .collect(toList());
        if (searchParams.getUnique()) {
            return viewRepository.countUniqueView(viewIds).stream()
                    .map(ViewMapper::mapToViewStats)
                    .collect(toList());
        } else {
            return viewRepository.countViewByIds(viewIds).stream()
                    .map(ViewMapper::mapToViewStats)
                    .collect(toList());
        }
    }
}

