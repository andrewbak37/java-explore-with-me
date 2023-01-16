package ru.yandex.practicum.mainservice.comments.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class NewCommentDto {

    @NotBlank
    private String text;
}