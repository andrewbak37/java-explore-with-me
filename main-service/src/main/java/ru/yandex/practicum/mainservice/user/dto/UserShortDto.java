package ru.yandex.practicum.mainservice.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class UserShortDto {

    @NotNull
    private final Long id;

    @NotBlank
    private final String name;
}
