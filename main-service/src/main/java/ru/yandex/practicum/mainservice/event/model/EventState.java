package ru.yandex.practicum.mainservice.event.model;

import java.util.Optional;

public enum EventState {
    PENDING,
    PUBLISHED,
    CANCELED;


    public static Optional<EventState> fromEventState(String stringState) {
        for (EventState state : values()) {
            if (state.name().equalsIgnoreCase(stringState)) {
                return Optional.of(state);
            }
        }
        return Optional.empty();
    }
}
