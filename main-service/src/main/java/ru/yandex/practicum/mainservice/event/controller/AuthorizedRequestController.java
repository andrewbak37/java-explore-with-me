package ru.yandex.practicum.mainservice.event.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.mainservice.event.dto.ParticipationRequestDto;
import ru.yandex.practicum.mainservice.event.service.RequestService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/requests")
public class AuthorizedRequestController {

    private final RequestService requestService;

    @GetMapping
    public List<ParticipationRequestDto> getUserRequests(@PathVariable("userId") Long userId) {
        return requestService.getUserRequests(userId);
    }

    @PostMapping
    public ParticipationRequestDto createUserRequest(@PathVariable("userId") Long userId,
                                                     @RequestParam("eventId") Long eventId) {
        return requestService.createUserRequest(userId, eventId);
    }

    @PatchMapping("/{requestId}/cancel")
    public ParticipationRequestDto cancelUserRequest(@PathVariable("userId") Long userId,
                                                     @PathVariable("requestId") Long requestId) {
        return requestService.cancelUserRequest(userId, requestId);
    }
}