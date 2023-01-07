package ru.yandex.practicum.mainservice.event.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.mainservice.event.dto.ParticipationRequestDto;
import ru.yandex.practicum.mainservice.event.dto.RequestMapper;
import ru.yandex.practicum.mainservice.event.model.Event;
import ru.yandex.practicum.mainservice.event.repository.EventRepository;
import ru.yandex.practicum.mainservice.event.repository.RequestRepository;
import ru.yandex.practicum.mainservice.event.request.Request;
import ru.yandex.practicum.mainservice.event.request.RequestState;
import ru.yandex.practicum.mainservice.exception.ObjectNotFoundException;
import ru.yandex.practicum.mainservice.exception.ValidationException;
import ru.yandex.practicum.mainservice.user.model.User;
import ru.yandex.practicum.mainservice.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Override
    public List<ParticipationRequestDto> getUserRequests(Long userId) {
        return requestRepository.findRequestsByRequester_userId(userId)
                .stream()
                .map(RequestMapper::mapToParticipationRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    public ParticipationRequestDto createUserRequest(Long userId, Long eventId) {
        User user = userRepository.findById(userId).orElseThrow(()
                -> new ObjectNotFoundException("User not found"));
        Event event = eventRepository.findById(eventId).orElseThrow(()
                -> new ObjectNotFoundException("Event not found"));
        Request request = RequestMapper.mapToRequest(user, event, LocalDateTime.now(), RequestState.PENDING);
        return RequestMapper.mapToParticipationRequestDto(requestRepository.save(request));
    }

    @Override
    public ParticipationRequestDto cancelUserRequest(Long userId, Long requestId) {
        userRepository.findById(userId).orElseThrow(()
                -> new ObjectNotFoundException("User not found"));
        Request request = requestRepository.findById(requestId).orElseThrow(()
                -> new ObjectNotFoundException("Request not found"));
        if (!request.getRequester().getUserId().equals(userId)) {
            throw new ValidationException("Validation failed");
        }
        request.setStatus(RequestState.CANCELED);

        return RequestMapper.mapToParticipationRequestDto(requestRepository.save(request));
    }
}