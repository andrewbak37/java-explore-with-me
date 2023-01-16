package ru.yandex.practicum.mainservice.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class UserDto {

    private final Long id;

    @Email
    @NotNull
    private final String email;

    @NotBlank
    private final String name;

}
