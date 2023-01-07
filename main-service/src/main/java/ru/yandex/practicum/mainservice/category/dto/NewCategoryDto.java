package ru.yandex.practicum.mainservice.category.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class NewCategoryDto {

    @NotBlank
    private String name;
}
