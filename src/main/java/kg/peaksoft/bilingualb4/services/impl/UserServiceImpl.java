package kg.peaksoft.bilingualb4.services.impl;

import kg.peaksoft.bilingualb4.api.payload.UserRequest;
import kg.peaksoft.bilingualb4.api.payload.UserResponse;
import kg.peaksoft.bilingualb4.exception.BadRequestException;
import kg.peaksoft.bilingualb4.exception.NotFoundException;
import kg.peaksoft.bilingualb4.model.entity.Question;
import kg.peaksoft.bilingualb4.model.entity.User;
import kg.peaksoft.bilingualb4.model.mappers.UserMapper;
import kg.peaksoft.bilingualb4.repository.UserRepository;
import kg.peaksoft.bilingualb4.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponse> findAll() {
        log.info("Fetching all users");
        return userMapper.mapToResponse(userRepository.findAll());
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

        User user = userMapper.mapToEntity(userRequest);
        User save = userRepository.save(user);
        return userMapper.mapToResponse(save);
    }

    @Override
    public Optional<User> findById(Long id) {
        boolean exists = userRepository.existsById(id);
        if (!exists) {
            throw new BadRequestException("You should write one of {id} to get Type");
        }
        return userRepository.findById(id);
    }

    @Override
    public UserResponse deleteById(Long id) {
        UserResponse userResponse = getById(id);
        boolean exists = userRepository.existsById(id);
        if (!exists) {
            throw new BadRequestException(
                    String.format("Type with id = %s does not exists", id)
            );
        }
        userRepository.deleteById(id);
        return userResponse;
    }

    @Override
    public UserResponse update(Long id, UserRequest userRequest) {
        User user = userRepository.getById(id);
        boolean exists = userRepository.existsById(id);
        User response;
        if (!exists) {
            throw new BadRequestException(
                    String.format("question with %d is already exists", id)
            );
        } else {
            response = userMapper.mapToEntity(userRequest);
            userRepository.save(response);
        }
        return userMapper.mapToResponse(response);

    }

    private UserResponse getById(Long id) {
        return userMapper.mapToResponse(userRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("User with id = %s does not exists", id)
        )));
    }
}
