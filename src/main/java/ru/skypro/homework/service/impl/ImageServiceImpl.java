package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.exception.ImageNotFoundException;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final AdRepository adRepository;

    public ImageServiceImpl(ImageRepository imageRepository, AdRepository adRepository) {
        this.imageRepository = imageRepository;
        this.adRepository = adRepository;
    }

    @Override
    public byte[] getImagePathById(String id) {
        Image image = imageRepository.findById(id).orElseThrow(ImageNotFoundException::new);
        return image.getImagePath();
    }
}