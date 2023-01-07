package ru.yandex.practicum.mainservice.category.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class CategoryDto {

    private final Long id;

    @NotBlank
    private final String name;
}
