package ru.yandex.practicum.mainservice.event.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.yandex.practicum.mainservice.event.request.RequestState;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class ParticipationRequestDto {

    private final Long id;

    @JsonFormat(pattern = "yy:MM:dd HH:mm:ss")
    private final LocalDateTime created;

    private final Long event;
    private final Long requester;
    private final RequestState status;
}
