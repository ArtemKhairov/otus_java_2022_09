package ru.otus.converter;

import ru.otus.model.User;
import ru.otus.dto.UserDto;

public interface UserConverter {

    User fromUserDto(UserDto userDto);
}
