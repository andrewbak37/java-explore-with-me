package ru.yandex.practicum.mainservice.compilation.dto;

import ru.yandex.practicum.mainservice.compilation.model.Compilation;
import ru.yandex.practicum.mainservice.event.dto.EventShortDto;
import ru.yandex.practicum.mainservice.event.model.Event;

import java.util.List;

public class CompilationMapper {

    public static Compilation mapToCompilation(NewCompilationDto newCompilationDto, List<Event> events) {
        return Compilation.builder()
                .events(events)
                .pinned(newCompilationDto.getPinned())
                .title(newCompilationDto.getTitle())
                .build();
    }

    public static CompilationDto mapToCompilationDto(Compilation compilation, List<EventShortDto> eventDtoList) {
        return CompilationDto.builder()
                .id(compilation.getId())
                .events(eventDtoList)
                .pinned(compilation.getPinned())
                .title(compilation.getTitle())
                .build();
    }
}
