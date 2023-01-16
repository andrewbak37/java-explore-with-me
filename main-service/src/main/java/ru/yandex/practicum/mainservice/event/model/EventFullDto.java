package ru.yandex.practicum.mainservice.event.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.yandex.practicum.mainservice.category.dto.CategoryDto;
import ru.yandex.practicum.mainservice.event.dto.LocationDto;
import ru.yandex.practicum.mainservice.user.dto.UserShortDto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class EventFullDto {
    private final Long id;
    @NotNull
    private final String title;
    @NotNull
    private final String annotation;
    @NotNull
    private final CategoryDto category;
    @NotNull
    private final Boolean paid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime eventDate;
    @NotNull
    private final UserShortDto initiator;
    private final Long views;
    private final Long confirmedRequests;
    private final String description;
    private final Integer participantLimit;
    private final EventState state;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdOn;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime publishedOn;
    private final LocationDto location;
    private final Boolean requestModeration;
}