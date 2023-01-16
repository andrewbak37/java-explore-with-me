package ru.yandex.practicum.mainservice.compilation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.yandex.practicum.mainservice.event.dto.EventShortDto;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class CompilationDto {

    @NotNull
    private final Long id;

    private final List<EventShortDto> events;

    @NotNull
    private final Boolean pinned;

    @NotNull
    private final String title;
}