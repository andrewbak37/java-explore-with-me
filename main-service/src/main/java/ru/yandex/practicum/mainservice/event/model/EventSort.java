package ru.yandex.practicum.mainservice.event.model;

import java.util.Optional;

public enum EventSort {
    EVENT_DATE,
    VIEWS;

    public static Optional<EventSort> fromEventSort(String stringSort) {
        for (EventSort sort : values()) {
            if (sort.name().equalsIgnoreCase(stringSort)) {
                return Optional.of(sort);
            }
        }
        return Optional.empty();
    }

}
