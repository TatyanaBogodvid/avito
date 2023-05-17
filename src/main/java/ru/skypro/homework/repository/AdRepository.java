package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.Ad;

/**
 * Интерфейс, содержащий методы для работы с базой данных объявлений
 */
@Repository
public interface AdRepository extends JpaRepository<Ad, Integer> {
}
