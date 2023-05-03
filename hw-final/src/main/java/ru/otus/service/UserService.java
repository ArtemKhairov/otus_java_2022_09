package ru.otus.service;

import ru.otus.dto.UserDto;
import ru.otus.model.User;

public interface UserService {

    void save(UserDto userDto);

    User getByLogin(String login);

    boolean isInvalidUser(UserDto userDto);

    String invalidUser(UserDto userDto);
}
