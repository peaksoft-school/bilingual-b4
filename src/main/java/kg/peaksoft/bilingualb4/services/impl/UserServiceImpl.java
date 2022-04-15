package kg.peaksoft.bilingualb4.services.impl;

import kg.peaksoft.bilingualb4.dto.request.UserRequest;
import kg.peaksoft.bilingualb4.dto.response.UserResponse;
import kg.peaksoft.bilingualb4.exception.BadRequestException;
import kg.peaksoft.bilingualb4.exception.NotFoundException;
import kg.peaksoft.bilingualb4.mappers.editMapper.UserEditMapper;
import kg.peaksoft.bilingualb4.mappers.viewMapper.UserViewMapper;
import kg.peaksoft.bilingualb4.models.User;
import kg.peaksoft.bilingualb4.repositories.UserRepository;
import kg.peaksoft.bilingualb4.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.google.common.base.Strings.isNullOrEmpty;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserEditMapper userEditMapper;
    private final UserViewMapper userViewMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    @Override
    public UserResponse registration(UserRequest userRequest) {
        String email = userRequest.getEmail();
        boolean exists = userRepository.existsByEmail(email);
        log.info("Saving new user {} to the database", userRequest.getUserName());
        if (exists) {
            throw new BadRequestException(
                    String.format("User with email = %s has already exists", email)
            );
        }
        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        userRequest.setPassword(encodedPassword);

        User user = userEditMapper.create(userRequest);
        User save = userRepository.save(user);
        return userViewMapper.view(save);
    }

    @Override
    public UserResponse findById(Long id) {
        if (id != null) {
            User user = getById(id);
            return userViewMapper.view(user);
        } else {
            throw new BadRequestException("You should write one of {id, name} to get Type");
        }
    }
    @Override
    public void deleteById(Long id) {
        boolean exists = userRepository.existsById(id);
        if (!exists) {
            throw new BadRequestException(
                    String.format("Type with id = %s does not exists", id)
            );
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse update(Long id, UserRequest userRequest) {
        User user = getById(id);

        String currentName = user.getUserName();
        String newName = userRequest.getUserName();

        if (!currentName.equals(newName)) {
            user.setUserName(newName);
        }

        String currentEmail = user.getEmail();
        String newEmail = userRequest.getEmail();

        if (!currentEmail.equals(newEmail)) {
            user.setEmail(newEmail);
        }

        String currentPassword = user.getPassword();
        String newPassword = userRequest.getPassword();

        if (!passwordEncoder.matches(newPassword, currentPassword)) {
            user.setPassword(passwordEncoder.encode(newPassword));
        }
        return userViewMapper.view(user);
    }

    private User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("User with id = %s does not exists", id)
        ));
    }
}
