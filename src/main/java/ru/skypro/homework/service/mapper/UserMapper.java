package ru.skypro.homework.service.mapper;

import ru.skypro.homework.dto.RegisterReqDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;

public interface UserMapper {
    UserDto mapToUserDto(User user);

    User mapToUser(UserDto userDto);

    User mapToUser(RegisterReqDto registerReqDto);
}
