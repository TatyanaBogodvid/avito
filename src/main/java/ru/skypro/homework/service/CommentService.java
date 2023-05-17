package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.ResponseWrapperCommentDto;

public interface CommentService {
    /**
     * Метод ищет и возвращает список всех комментариев к объявлению по id объявления
     */
    ResponseWrapperCommentDto getCommentsDto(Integer adId);

    /**
     * Метод создает комментарий к объявлению по id объявления
     */
    CommentDto createCommentDto(Integer adId, CommentDto commentDto);

    /**
     * Метод удаляет комментарий к объявлению по id объявления
     */
    boolean removeCommentDto(Integer adId, Integer commentId);

    /**
     * Метод редактирует комментарий к объявлению по id
     */
    CommentDto updateCommentDto(Integer adId, Integer commentId, CommentDto commentDto);

    /**
     * Метод проверяет наличие доступа к комментарию по id
     */
    boolean checkAccess(Integer id);
}
