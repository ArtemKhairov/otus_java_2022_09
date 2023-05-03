package ru.otus.service.impl;

import ru.otus.model.User;
import ru.otus.service.UserService;
import ru.otus.converter.UserConverter;
import ru.otus.dao.UserDAO;
import ru.otus.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class.getName());

    private static final String FIELD_IS_EMPTY = "Login or password shouldn't be empty";
    private static final String INVALID_FIELD = "Login or password shouldn't be less than 4 symbols";
    private static final String USER_IS_PRESENT = "User with login {} is already present";

    private final UserDAO userDAO;
    private final UserConverter userConverter;

    @Override
    @Transactional
    public void save(UserDto userDto) {
        User user = userConverter.fromUserDto(userDto);

        userDAO.save(user);

        LOGGER.info("New user : {}", user);
    }

    @Override
    @Transactional
    public User getByLogin(String login) {
        return userDAO.getByLogin(login);
    }

    @Override
    @Transactional
    public boolean isInvalidUser(UserDto userDto) {
        return userDto.getLogin().length() < 4 || userDto.getPassword().length() < 4 || isUserPresent(userDto);
    }

    @Override
    @Transactional
    public String invalidUser(UserDto userDto) {
        String login = userDto.getLogin();
        String password = userDto.getPassword();

        if (login.isEmpty() || password.isEmpty()) {
            LOGGER.error(FIELD_IS_EMPTY);

            return FIELD_IS_EMPTY;
        }

        if (isUserPresent(userDto)) {
            LOGGER.error(USER_IS_PRESENT, login);

            return String.format(USER_IS_PRESENT.replace("{}", "%s"), login);
        }

        LOGGER.error(INVALID_FIELD);

        return INVALID_FIELD;
    }

    @Transactional
    public boolean isUserPresent(UserDto userDto) {
        String login = userDto.getLogin();

        return userDAO.getAll().stream().anyMatch(user -> login.equals(user.getLogin()));
    }
}
