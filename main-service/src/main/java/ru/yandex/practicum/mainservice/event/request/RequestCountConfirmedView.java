package ru.yandex.practicum.mainservice.event.request;

public interface RequestCountConfirmedView {
    Long getEventId();
    Long getRequestCount();
}
