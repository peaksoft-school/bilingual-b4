package kg.peaksoft.bilingualb4.services;

import kg.peaksoft.bilingualb4.dto.request.UserRequest;
import kg.peaksoft.bilingualb4.dto.response.UserResponse;
import kg.peaksoft.bilingualb4.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    UserResponse registration(UserRequest userRequest);

    UserResponse findById(Long id);

    void deleteById(Long id);

    UserResponse update(Long id, UserRequest userRequest);

}
