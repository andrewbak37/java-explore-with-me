package ru.yandex.practicum.mainservice.compilation.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

import java.util.List;

@Getter
@Setter
public class NewCompilationDto {

    private List<Long> events;

    private Boolean pinned;

    @NotNull
    private String title;
}