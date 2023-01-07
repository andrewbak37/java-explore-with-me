package ru.yandex.practicum.mainservice.user.service;

import ru.yandex.practicum.mainservice.user.dto.UserDto;
import ru.yandex.practicum.mainservice.user.dto.UserRequest;

import java.util.List;

public interface UserService {

    List<UserDto> getUsers(List<Long> userIds);

    UserDto createUser (UserRequest userRequest);

    void deleteUser (Long userId);
}
