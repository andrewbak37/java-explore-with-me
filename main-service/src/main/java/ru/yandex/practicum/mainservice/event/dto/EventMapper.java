package ru.yandex.practicum.mainservice.event.dto;

import ru.yandex.practicum.mainservice.category.dto.CategoryDto;
import ru.yandex.practicum.mainservice.category.model.Category;
import ru.yandex.practicum.mainservice.event.model.Event;
import ru.yandex.practicum.mainservice.event.model.EventFullDto;
import ru.yandex.practicum.mainservice.event.model.EventState;
import ru.yandex.practicum.mainservice.event.model.Location;
import ru.yandex.practicum.mainservice.user.dto.UserShortDto;
import ru.yandex.practicum.mainservice.user.model.User;

import java.time.LocalDateTime;

public class EventMapper {

    public static Event mapToEvent(NewEventDto newEventDto, User user, Category category) {
        return Event.builder()
                .initiator(user)
                .title(newEventDto.getTitle())
                .annotation(newEventDto.getAnnotation())
                .category(category)
                .description(newEventDto.getDescription())
                .eventDate(newEventDto.getEventDate())
                .location(mapToLocation(newEventDto.getLocation()))
                .paid(newEventDto.getPaid())
                .participantLimit(newEventDto.getParticipantLimit())
                .requestModeration(newEventDto.getRequestModeration())
                .createdOn(LocalDateTime.now())
                .state(EventState.PENDING)
                .build();
    }

    private static Location mapToLocation(LocationDto location) {
        return Location.builder()
                .lon(location.getLon())
                .lat(location.getLat())
                .build();
    }

    private static LocationDto mapToLocationDto(Location location) {
        return LocationDto.builder()
                .lat(location.getLat())
                .lon(location.getLon())
                .build();
    }

    public static EventDto mapToEventDto(Event event, Long confirmedRequest, Long views) {
        return EventDto.builder()
                .id(event.getId())
                .title(event.getTitle())
                .annotation(event.getAnnotation())
                .category(new CategoryDto(event.getCategory().getId(),
                        event.getCategory().getName()))
                .paid(event.getPaid())
                .eventDate(event.getEventDate())
                .initiator(mapToUserShortDto(event.getInitiator()))
                .location(mapToLocationDto(event.getLocation()))
                .description(event.getDescription())
                .state(event.getState())
                .createdOn(event.getCreatedOn())
                .participantLimit(event.getParticipantLimit())
                .requestModeration(event.getRequestModeration())
                .build();
    }

    private static UserShortDto mapToUserShortDto(User user) {
        return UserShortDto.builder()
                .name(user.getName())
                .id(user.getUserId())
                .build();
    }

    public static EventShortDto mapToShortDto(Event event, Long confirmedRequests, Long views) {
        return EventShortDto.builder()
                .id(event.getId())
                .annotation(event.getAnnotation())
                .category(new CategoryDto(event.getCategory().getId(),event.getCategory().getName()))
                .confirmedRequests(confirmedRequests)
                .participantLimit(event.getParticipantLimit())
                .eventDate(event.getEventDate())
                .initiator(new UserShortDto(event.getInitiator().getUserId(),event.getInitiator().getName()))
                .paid(event.getPaid())
                .title(event.getTitle())
                .views(views)
                .build();
    }

    public static EventFullDto mapToFullDto(Event event, Long confirmedRequests, Long views) {
        return EventFullDto.builder()
                .id(event.getId())
                .createdOn(event.getCreatedOn())
                .annotation(event.getAnnotation())
                .description(event.getDescription())
                .category(new CategoryDto(event.getCategory().getId(), event.getCategory().getName()))
                .state(event.getState())
                .confirmedRequests(confirmedRequests)
                .eventDate(event.getEventDate())
                .location(new LocationDto(event.getLocation().getLat(), event.getLocation().getLon()))
                .publishedOn(event.getPublishedOn())
                .paid(event.getPaid())
                .participantLimit(event.getParticipantLimit())
                .requestModeration(event.getRequestModeration())
                .title(event.getTitle())
                .views(views)
                .initiator(new UserShortDto(event.getInitiator().getUserId(), event.getInitiator().getName()))
                .build();
    }
}
