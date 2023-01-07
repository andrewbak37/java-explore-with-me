package ru.yandex.practicum.mainservice.event.service;

import ru.yandex.practicum.mainservice.event.dto.*;
import ru.yandex.practicum.mainservice.event.model.EventFullDto;
import ru.yandex.practicum.mainservice.event.model.EventSearchParams;

import java.util.List;

public interface EventService {

    List<EventShortDto> getEvents(EventSearchParams params, Integer from, Integer size);

    EventFullDto getEvent(Long eventId, String uri, String ip);

    List<EventShortDto> getUserEvents(Long userId, Integer from, Integer size);

    EventFullDto createUserEvent(NewEventDto newEventDto, Long userId);

    EventFullDto updateUserEvent(Long userId, UpdateEventRequest updateEvent);

    EventFullDto getUserEvent(Long userId, Long eventId);

    EventFullDto cancelUserEvent(Long userId, Long eventId);

    List<ParticipationRequestDto> getUserEventRequests(Long userId, Long eventId);

    ParticipationRequestDto confirmEventRequest(Long userId, Long eventId, Long reqId);

    ParticipationRequestDto rejectEventRequest(Long userId, Long eventId, Long reqId);

    List<EventFullDto> searchEvents(EventSearchParams params, Integer from, Integer size);

    EventFullDto editEvent(Long eventId, AdminUpdateEventRequest event);

    EventFullDto publishEvent(Long eventId);

    EventFullDto rejectEvent(Long eventId);
}
