package ru.skypro.homework.service;

/**
 * Интерфейс сервисного класса ImageServiceImpl, содержащий набор CRUD операций над объектом Image
 */
public interface ImageService {
    byte[] getImagePathById(String id);
}
