package kg.peaksoft.bilingualb4.services;

import kg.peaksoft.bilingualb4.api.payload.UserRequest;
import kg.peaksoft.bilingualb4.api.payload.UserResponse;
import kg.peaksoft.bilingualb4.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserResponse> findAll();

    UserResponse registration(UserRequest userRequest);

    UserResponse findById(Long id);

    UserResponse deleteById(Long id);

    UserResponse update(Long id, UserRequest userRequest);

}
