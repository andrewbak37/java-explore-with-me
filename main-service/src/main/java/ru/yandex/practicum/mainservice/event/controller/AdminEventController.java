package ru.yandex.practicum.mainservice.event.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.mainservice.event.dto.AdminUpdateEventRequest;
import ru.yandex.practicum.mainservice.event.model.EventFullDto;
import ru.yandex.practicum.mainservice.event.model.EventSearchParams;
import ru.yandex.practicum.mainservice.event.model.EventState;
import ru.yandex.practicum.mainservice.event.service.EventService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/events")
public class AdminEventController {

    private final EventService eventService;

    @GetMapping
    public List<EventFullDto> searchEvents(@RequestParam(value = "users", required = false) List<Long> users,
                                           @RequestParam(value = "states", required = false) List<String> states,
                                           @RequestParam(value = "categories", required = false) List<Long> categories,
                                           @RequestParam(value = "rangeStart", required = false) String rangeStart,
                                           @RequestParam(value = "rangeEnd", required = false) String rangeEnd,
                                           @RequestParam(value = "from", defaultValue = "0") Integer from,
                                           @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (states == null) return Collections.emptyList();
        List<EventState> reqStates = states
                .stream()
                .map(EventState::fromEventState)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        EventSearchParams params =
                new EventSearchParams(null, users, reqStates, categories, null, rangeStart, rangeEnd, null, null);
        return eventService.searchEvents(params, from, size);
    }

    @PutMapping("/{eventId}")
    public EventFullDto editEvent(@PathVariable("eventId") Long eventId,
                                  @RequestBody AdminUpdateEventRequest event) {
        return eventService.editEvent(eventId, event);
    }

    @PatchMapping("/{eventId}/publish")
    public EventFullDto publishEvent(@PathVariable("eventId") Long eventId) {
        return eventService.publishEvent(eventId);
    }

    @PatchMapping("/{eventId}/reject")
    public EventFullDto rejectEvent(@PathVariable("eventId") Long eventId) {
        return eventService.rejectEvent(eventId);
    }
}