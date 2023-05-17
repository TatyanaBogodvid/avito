package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.User;

import java.util.Optional;

/**
 * Интерфейс, содержащий методы для работы с базой данных пользователей
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Метод ищет и возвращает пользователя по его email
     */
    Optional<User> findByEmail(String email);
}
