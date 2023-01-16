package ru.yandex.practicum.mainservice.compilation.service;

import ru.yandex.practicum.mainservice.compilation.dto.CompilationDto;
import ru.yandex.practicum.mainservice.compilation.dto.NewCompilationDto;

import java.util.List;


public interface CompilationService {
    List<CompilationDto> getCompilations(Integer from, Integer size);

    CompilationDto getCompilation(Long compId);

    CompilationDto createCompilation(NewCompilationDto newCompilationDto);

    void deleteCompilation(Long compId);

    void deleteCompilationEvent(Long compId, Long eventId);

    void addCompilationEvent(Long compId, Long eventId);

    void unpinCompilation(Long compId);

    void pinCompilation(Long compId);


}
