package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;

import java.util.Optional;

/**
 * Интерфейс сервисного класса UserServiceImpl, содержащий набор CRUD операций над объектом User
 */
public interface UserService {
    /**
     * Метод ищет авторизованного пользователя
     */
    Optional<User> findAuthUser();

    /**
     * Метод возвращает Dto авторизованного пользователя
     */
    UserDto getUserDto();

    /**
     * Метод редактирует данные авторизованного пользователя
     */
    UserDto updateUserDto(UserDto userDto);

    /**
     * Метод обновляет аватар пользователя
     */
    void updateUserImage(MultipartFile image);
}
