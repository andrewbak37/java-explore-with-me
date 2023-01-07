package ru.yandex.practicum.mainservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private final String error;
    private String status;
}
