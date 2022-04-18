package kg.peaksoft.bilingualb4.services;

import kg.peaksoft.bilingualb4.api.payload.UserRequest;
import kg.peaksoft.bilingualb4.api.payload.UserResponse;
import kg.peaksoft.bilingualb4.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    UserResponse registration(UserRequest userRequest);

    Optional<User> findById(Long id);

    User getById(Long id);

    void deleteById(Long id);

    UserResponse update(Long id, UserRequest userRequest);

}
