package ru.yandex.practicum.mainservice.event.request;

import java.util.Optional;

public enum RequestState {
    PENDING,
    CONFIRMED,
    CANCELED,
    REJECTED;

    public static Optional<RequestState> fromRequestState(String stringState) {
        for (RequestState state : values()) {
            if (state.name().equalsIgnoreCase(stringState)) {
                return Optional.of(state);
            }
        }
        return Optional.empty();
    }
}
