package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;

/**
 * Интерфейс сервисного класса AdServiceImpl, содержащий набор CRUD операций над объектом Ad
 */
public interface AdService {
    /**
     * Метод ищет и возвращает список всех объявлений
     */
    ResponseWrapperAdsDto getAllAdsDto();

    /**
     * Метод создает объявление
     */
    AdsDto createAds(CreateAdsDto adDto, MultipartFile image);

    /**
     * Метод ищет и возвращает объявление по id
     */
    FullAdsDto getFullAdDto(Integer id);

    /**
     * Метод удаляет объявление по id
     */
    boolean removeAdDto(Integer id);

    /**
     * Метод редактирует объявление по id
     */
    AdsDto updateAdDto(Integer id, CreateAdsDto adDto);

    /**
     * Метод ищет и возвращает список всех объявлений авторизированного пользователя
     */
    ResponseWrapperAdsDto getAllUserAdsDto();

    /**
     * Метод обновляет изображение к объявлению по id
     */
    void updateImageAdDto(Integer id, MultipartFile image);

    /**
     * Метод проверяет наличие доступа к объявлению по id
     */
    boolean checkAccess(Integer id);
}
