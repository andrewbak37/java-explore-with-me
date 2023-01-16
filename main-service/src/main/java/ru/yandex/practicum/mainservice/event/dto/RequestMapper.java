package ru.yandex.practicum.mainservice.event.dto;


import ru.yandex.practicum.mainservice.event.model.Event;
import ru.yandex.practicum.mainservice.event.request.Request;
import ru.yandex.practicum.mainservice.event.request.RequestState;
import ru.yandex.practicum.mainservice.user.model.User;

import java.time.LocalDateTime;

public class RequestMapper {

    public static ParticipationRequestDto mapToParticipationRequestDto(Request request) {
        return new ParticipationRequestDto(
                request.getId(),
                request.getCreated(),
                request.getEvent().getId(),
                request.getRequester().getUserId(),
                request.getStatus()
        );
    }

    public static Request mapToRequest(User user, Event event, LocalDateTime now, RequestState state) {
        Request request = new Request();
        request.setRequester(user);
        request.setEvent(event);
        request.setCreated(now);
        request.setStatus(state);
        return request;
    }
}
