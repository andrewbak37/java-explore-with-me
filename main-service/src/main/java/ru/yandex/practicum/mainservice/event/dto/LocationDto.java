package ru.yandex.practicum.mainservice.event.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LocationDto {
    private Float lat;
    private Float lon;
}
