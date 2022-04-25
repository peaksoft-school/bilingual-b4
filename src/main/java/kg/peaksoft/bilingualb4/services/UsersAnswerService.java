package kg.peaksoft.bilingualb4.services;

import kg.peaksoft.bilingualb4.api.payload.UsersAnswerRequest;
import kg.peaksoft.bilingualb4.api.payload.UsersAnswerResponse;

public interface UsersAnswerService {

    UsersAnswerResponse save(Long id,UsersAnswerRequest usersAnswerRequest);

    void toCancel(Long id);
}
