package ru.yandex.practicum.mainservice.category.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@ToString
@RequiredArgsConstructor
public class CategoryDto {

    private final Long id;

    @NotBlank
    private final String name;
}
