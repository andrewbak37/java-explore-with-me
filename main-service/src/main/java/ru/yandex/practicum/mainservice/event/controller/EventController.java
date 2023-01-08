package ru.yandex.practicum.mainservice.event.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.mainservice.event.dto.EventShortDto;
import ru.yandex.practicum.mainservice.event.model.EventFullDto;
import ru.yandex.practicum.mainservice.event.model.EventSearchParams;
import ru.yandex.practicum.mainservice.event.service.EventService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @GetMapping
    public List<EventShortDto> getEvents(@RequestParam(value = "text", required = false) String text,
                                         @RequestParam(value = "categories", required = false) List<Long> categories,
                                         @RequestParam(value = "paid", required = false) Boolean paid,
                                         @RequestParam(value = "rangeStart", required = false) String rangeStart,
                                         @RequestParam(value = "rangeEnd", required = false) String rangeEnd,
                                         @RequestParam(value = "onlyAvailable", defaultValue = "false") Boolean onlyAvailable,
                                         @RequestParam(value = "sort", required = false) String sort,
                                         @RequestParam(value = "from", defaultValue = "0") Integer from,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size,
                                         HttpServletRequest request) {
        EventSearchParams params =
                new EventSearchParams(text, null, null, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort);
        String requestUri = request.getRequestURI();
        String userIp = request.getRemoteAddr();
        return eventService.getEvents(params, from, size, requestUri, userIp);
    }

    @GetMapping("/{eventId}")
    public EventFullDto getEvent(@PathVariable("eventId") Long eventId,
                                 HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        String userIp = request.getRemoteAddr();
        return eventService.getEvent(eventId, requestUri, userIp);
    }
}