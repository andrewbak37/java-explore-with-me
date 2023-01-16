package ru.yandex.practicum.mainservice.compilation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.mainservice.compilation.dto.CompilationDto;
import ru.yandex.practicum.mainservice.compilation.dto.NewCompilationDto;
import ru.yandex.practicum.mainservice.compilation.service.CompilationService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/compilations")
public class AdminCompilationController {

    private final CompilationService compilationService;

    @PostMapping
    public CompilationDto createCompilation(@Valid @RequestBody NewCompilationDto compilation) {
        return compilationService.createCompilation(compilation);
    }

    @PatchMapping("/{compId}/events/{eventId}")
    public void addCompilationEvent(@PathVariable("compId") Long compId,
                                    @PathVariable("eventId") Long eventId) {
        compilationService.addCompilationEvent(compId, eventId);
    }

    @DeleteMapping("/{compId}/pin")
    public void unpinCompilation(@PathVariable("compId") Long compId) {
        compilationService.unpinCompilation(compId);
    }

    @PatchMapping("/{compId}/pin")
    public void pinCompilation(@PathVariable("compId") Long compId) {
        compilationService.pinCompilation(compId);
    }

    @DeleteMapping("/{compId}")
    public void deleteCompilation(@PathVariable("compId") Long compId) {
        compilationService.deleteCompilation(compId);
    }

    @DeleteMapping("/{compId}/events/{eventId}")
    public void deleteCompilationEvent(@PathVariable("compId") Long compId,
                                       @PathVariable("eventId") Long eventId) {
        compilationService.deleteCompilationEvent(compId, eventId);
    }
}
