package kg.peaksoft.bilingualb4.services.impl;

import kg.peaksoft.bilingualb4.dto.request.UserRequest;
import kg.peaksoft.bilingualb4.dto.response.UserResponse;
import kg.peaksoft.bilingualb4.exception.BadRequestException;
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
    public UserResponse save(Long id, UserRequest userRequest) {
        log.info("Saving new user {} to the database", userRequest.getUserName());
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        System.out.println(userRequest.getPassword());
        return userViewMapper.view(
                userRepository.save(
                        userEditMapper.create(id, userRequest)));
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse update(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).get();
        userEditMapper.Update(user, userRequest);
        return userViewMapper.view(userRepository.save(user));
    }
}
