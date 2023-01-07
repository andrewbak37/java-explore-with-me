package ru.yandex.practicum.statsservice.stat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.statsservice.stat.model.EndpointHit;
import ru.yandex.practicum.statsservice.stat.model.StatsSearchParams;
import ru.yandex.practicum.statsservice.stat.model.ViewStats;
import ru.yandex.practicum.statsservice.stat.service.ViewService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ViewController {

    private final ViewService viewService;

    @PostMapping
    @RequestMapping("/hit")
    public void postStat(@Valid @RequestBody EndpointHit endpointHit) {
        viewService.postStat(endpointHit);
    }

    @GetMapping
    @RequestMapping("/stats")
    public List<ViewStats> getStats(@RequestParam("start") String start,
                                    @RequestParam(value = "end") String end,
                                    @RequestParam(value = "uris", required = false) List<String> uris,
                                    @RequestParam(value = "unique", defaultValue = "false") Boolean unique) {
        StatsSearchParams searchParams = new StatsSearchParams(start, end, uris, unique);
        return viewService.getStats(searchParams);
    }
}