package ru.yandex.practicum.mainservice.exception;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handleNotFoundHandler(ObjectNotFoundException e) {
        return new ErrorResponse(NOT_FOUND.name(), NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleValidationException(final ValidationException e) {
        return new ErrorResponse(BAD_REQUEST.name(), BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleUnrecognizedPropertyException(final UnrecognizedPropertyException e) {
        return new ErrorResponse(BAD_REQUEST.name(), BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(CONFLICT)
    public ErrorResponse handleDatabaseException(final SQLException e) {
        return new ErrorResponse(CONFLICT.name(), CONFLICT.value(), e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(FORBIDDEN)
    public ErrorResponse handleForbiddenException(final ForbiddenException e) {
        return new ErrorResponse(FORBIDDEN.name(), FORBIDDEN.value(), e.getMessage());
    }
}
