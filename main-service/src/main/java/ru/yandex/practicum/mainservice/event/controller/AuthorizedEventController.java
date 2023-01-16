package ru.yandex.practicum.mainservice.event.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.mainservice.event.dto.EventShortDto;
import ru.yandex.practicum.mainservice.event.dto.NewEventDto;
import ru.yandex.practicum.mainservice.event.dto.ParticipationRequestDto;
import ru.yandex.practicum.mainservice.event.dto.UpdateEventRequest;
import ru.yandex.practicum.mainservice.event.model.EventFullDto;
import ru.yandex.practicum.mainservice.event.service.EventService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/events")
public class AuthorizedEventController {

    private final EventService eventService;

    @GetMapping
    public List<EventShortDto> getUserEvents(@PathVariable("userId") Long userId,
                                             @RequestParam(value = "from", defaultValue = "0") Integer from,
                                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return eventService.getUserEvents(userId, from, size);
    }

    @PostMapping
    public EventFullDto createUserEvent(@Valid @RequestBody NewEventDto newEventDto,
                                        @PathVariable("userId") Long userId) {
        return eventService.createUserEvent(newEventDto, userId);
    }

    @PatchMapping
    public EventFullDto updateUserEvent(@Valid @RequestBody UpdateEventRequest updateEvent,
                                        @PathVariable("userId") Long userId) {
        return eventService.updateUserEvent(userId, updateEvent);
    }

    @GetMapping("/{eventId}")
    public EventFullDto getUserEvent(@PathVariable("userId") Long userId,
                                     @PathVariable("eventId") Long eventId) {
        return eventService.getUserEvent(userId, eventId);
    }

    @PatchMapping("/{eventId}")
    public EventFullDto cancelUserEvent(@PathVariable("userId") Long userId,
                                        @PathVariable("eventId") Long eventId) {
        return eventService.cancelUserEvent(userId, eventId);
    }

    @GetMapping("/{eventId}/requests")
    public List<ParticipationRequestDto> getUserEventRequests(@PathVariable("userId") Long userId,
                                                              @PathVariable("eventId") Long eventId) {
        return eventService.getUserEventRequests(userId, eventId);
    }

    @PatchMapping("/{eventId}/requests/{reqId}/confirm")
    public ParticipationRequestDto confirmEventRequest(@PathVariable("userId") Long userId,
                                                       @PathVariable("eventId") Long eventId,
                                                       @PathVariable("reqId") Long reqId) {
        return eventService.confirmEventRequest(userId, eventId, reqId);
    }

    @PatchMapping("/{eventId}/requests/{reqId}/reject")
    public ParticipationRequestDto rejectEventRequest(@PathVariable("userId") Long userId,
                                                      @PathVariable("eventId") Long eventId,
                                                      @PathVariable("reqId") Long reqId) {
        return eventService.rejectEventRequest(userId, eventId, reqId);
    }
}