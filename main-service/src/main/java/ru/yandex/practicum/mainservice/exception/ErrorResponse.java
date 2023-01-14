package ru.yandex.practicum.mainservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private Long timestamp = Instant.now().getEpochSecond();
    private String error;
    private Integer status;
    private String message;

    public ErrorResponse(String error, Integer status, String message) {
        this.error = error;
        this.status = status;
        this.message = message;
    }
}
