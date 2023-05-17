package ru.skypro.homework.service.mapper;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.entity.Comment;

public interface CommentMapper {
    CommentDto mapToCommentDto(Comment comment);

    Comment mapToComment(CommentDto commentDto);
}
