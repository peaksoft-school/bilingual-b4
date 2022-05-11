package kg.peaksoft.bilingualb4.services;

import kg.peaksoft.bilingualb4.api.payload.UsersAnswerRequest;
import kg.peaksoft.bilingualb4.api.payload.UsersAnswerResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsersAnswerService {

    UsersAnswerResponse save(Long id, UsersAnswerRequest usersAnswerRequest, UserDetails userDetails);

    void toCancel(Long id);
}
