package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RegisterReqDto;
import ru.skypro.homework.dto.Role;

/**
 * Интерфейс сервисного класса AuthServiceImpl
 */
public interface AuthService {
    /**
     * Метод авторизует пользователя в системеl
     */
    boolean login(String userName, String password);

    /**
     * Метод регистрирует пользователя в системе
     */
    boolean register(RegisterReqDto registerReqDto, Role role);

    /**
     * Метод обновляет пароль пользователя
     */
    void changePassword(NewPasswordDto newPasswordDto);
}