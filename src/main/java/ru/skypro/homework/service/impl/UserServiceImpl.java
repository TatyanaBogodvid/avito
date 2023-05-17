package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.mapper.UserMapper;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    /**
     * Метод находит пользователя по email и возвращает его данные: имя пользователя и пароль
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("User with username " + username + " doesn't exists"));
    }

    /**
     * Метод ищет авторизованного пользователя
     * @return {@link UserRepository#findByEmail(String)}
     */
    @Override
    public Optional<User> findAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userRepository.findByEmail(currentPrincipalName);
    }

    /**
     * Метод достает пользователя из базы данных {@link UserService#findAuthUser()} и
     * конвертирует его в {@link UserDto}
     * @return {@link UserMapper#mapToUser(UserDto)}
     * @see UserMapper
     */
    @Override
    public UserDto getUserDto() {
        Optional<User> currentUser = findAuthUser();
        UserDto currentUserDto = new UserDto();
        if (currentUser.isPresent()) {
            currentUserDto = userMapper.mapToUserDto(currentUser.get());
        }
        return currentUserDto;
    }

    /**
     * Метод достает пользователя из базы данных {@link UserService#findAuthUser()},
     * редактирует данные и сохраняет в базе
     * @param newUserDto
     * @return {@link UserRepository#save(Object)}, {@link UserMapper#mapToUser(UserDto)}
     * @see UserMapper
     */
    @Override
    public UserDto updateUserDto(UserDto newUserDto) {
        Optional<User> currentUser = findAuthUser();
        User newCurrentUser = new User();
        if (currentUser.isPresent()) {
            newCurrentUser = currentUser.get();
            newCurrentUser.setFirstName(newUserDto.getFirstName());
            newCurrentUser.setLastName(newUserDto.getLastName());
            newCurrentUser.setPhone(newUserDto.getPhone());
            //другие поля остаются теми же
            userRepository.save(newCurrentUser);
        }
        return userMapper.mapToUserDto(newCurrentUser);
    }

    /**
     * Метод достает пользователя из базы данных {@link UserService#findAuthUser()},
     * устанавливает или обновляет его аватар, затем сохраняет изменения в базе данных:
     * {@link ImageRepository#saveAndFlush(Object)}, {@link UserRepository#save(Object)}
     * @param image
     * @throws UsernameNotFoundException если пользователь не найден
     */
    @Override
    public void updateUserImage(MultipartFile image) {
        User user = findAuthUser().orElseThrow(UserNotFoundException::new);
        Image oldImage = user.getImage();
        if (oldImage == null) {
            Image newImage = new Image();
            try {
                byte[] bytes = image.getBytes();
                newImage.setImagePath(bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            newImage.setId(UUID.randomUUID().toString());
            Image savedImage = imageRepository.saveAndFlush(newImage);
            user.setImage(savedImage);
        } else {
            try {
                byte[] bytes = image.getBytes();
                oldImage.setImagePath(bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Image savedImage = imageRepository.saveAndFlush(oldImage);
            user.setImage(savedImage);
        }
        userRepository.save(user);
    }
}