package ru.yandex.practicum.mainservice.user.dto;

import ru.yandex.practicum.mainservice.user.model.User;

public class UserMapper {

    public static User mapToUser(UserRequest userRequest) {
    return User.builder()
                .email(userRequest.getEmail())
                .name(userRequest.getName())
                .build();
    }

    public static UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id(user.getUserId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }
}
