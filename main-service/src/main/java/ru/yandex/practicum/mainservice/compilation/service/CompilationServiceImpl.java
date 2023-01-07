package ru.yandex.practicum.mainservice.compilation.service;


import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.client.clients.EventClient;
import ru.yandex.practicum.mainservice.compilation.dto.CompilationDto;
import ru.yandex.practicum.mainservice.compilation.dto.CompilationMapper;
import ru.yandex.practicum.mainservice.compilation.dto.NewCompilationDto;
import ru.yandex.practicum.mainservice.compilation.model.Compilation;
import ru.yandex.practicum.mainservice.compilation.repository.CompilationRepository;
import ru.yandex.practicum.mainservice.event.dto.EventMapper;
import ru.yandex.practicum.mainservice.event.dto.EventShortDto;
import ru.yandex.practicum.mainservice.event.model.Event;
import ru.yandex.practicum.mainservice.event.repository.EventRepository;
import ru.yandex.practicum.mainservice.event.repository.RequestRepository;
import ru.yandex.practicum.mainservice.exception.ObjectNotFoundException;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {

    private final CompilationRepository compilationRepository;
    private final RequestRepository requestRepository;
    private final EventRepository eventRepository;
    private final EventClient eventClient;


    @Override
    public List<CompilationDto> getCompilations(Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from / size, size);
        List<Compilation> compilations = compilationRepository.findAll(pageable).toList();
        return compilations
                .stream()
                .map(compilation -> {
                    List<EventShortDto> eventShortDtoList = compilation.getEvents()
                            .stream()
                            .map(event -> {
                                Long confirmedRequest =
                                        requestRepository.countConfirmedRequests(event.getId());
                                Long views =
                                        eventClient.getEventViews(event.getCreatedOn(), event.getId());
                                return EventMapper.mapToShortDto(event, confirmedRequest, views);
                            })
                            .collect(Collectors.toList());
                    return CompilationMapper.mapToCompilationDto(compilation, eventShortDtoList);
                })
                .collect(Collectors.toList());
    }

    @Override
    public CompilationDto getCompilation(Long compId) {
        Compilation compilation = compilationRepository.findById(compId).orElseThrow(()
                -> new ObjectNotFoundException("Compilation not found"));
        List<EventShortDto> eventShortDtoList = compilation.getEvents()
                .stream()
                .map(event -> {
                    Long confirmRequest = requestRepository
                            .countConfirmedRequests(event.getId());
                    Long views = eventClient.getEventViews(event.getCreatedOn(), event.getId());
                    return EventMapper.mapToShortDto(event, confirmRequest, views);
                })
                .collect(Collectors.toList());
        return CompilationMapper.mapToCompilationDto(compilation, eventShortDtoList);

    }

    @Override
    public CompilationDto createCompilation(NewCompilationDto newCompilationDto) {
        List<Event> events = eventRepository.findEventsByIds(newCompilationDto.getEvents());
        List<EventShortDto> eventShortDtoList = events
                .stream()
                .map(event -> {
                    Long confirmedRequests = requestRepository.countConfirmedRequests(event.getId());
                    Long views = eventClient.getEventViews(event.getCreatedOn(), event.getId());
                    return EventMapper.mapToShortDto(event, confirmedRequests, views);
                })
                .collect(Collectors.toList());
        Compilation compilation = CompilationMapper.mapToCompilation(newCompilationDto, events);
        return CompilationMapper.mapToCompilationDto(compilationRepository.save(compilation), eventShortDtoList);
    }

    @Override
    public void deleteCompilation(Long compId) {
        compilationRepository.deleteById(compId);
    }

    @Override
    public void deleteCompilationEvent(Long compId, Long eventId) {
        Compilation compilation = compilationRepository.findById(compId).orElseThrow(()
                -> new ObjectNotFoundException("Compilation not found"));
        Event event = eventRepository.findById(eventId).orElseThrow(()
                -> new ObjectNotFoundException("Event not found"));
        compilation.getEvents().remove(event);
        compilationRepository.save(compilation);
    }

    @Override
    public void addCompilationEvent(Long compId, Long eventId) {
        Compilation compilation = compilationRepository.findById(compId).orElseThrow(()
                -> new ObjectNotFoundException("Compilation not found"));
        Event event = eventRepository.findById(eventId).orElseThrow(()
                -> new ObjectNotFoundException("Event not found"));
        compilation.getEvents().add(event);
        compilationRepository.save(compilation);
    }

    @Override
    public void unpinCompilation(Long compId) {
        Compilation compilation = compilationRepository.findById(compId).orElseThrow(()
                -> new ObjectNotFoundException("Compilation not found"));
        compilation.setPinned(false);
        compilationRepository.save(compilation);
    }

    @Override
    public void pinCompilation(Long compId) {
        Compilation compilation = compilationRepository.findById(compId).orElseThrow(()
                -> new ObjectNotFoundException("Compilation not found"));
        compilation.setPinned(true);
        compilationRepository.save(compilation);
    }
}
