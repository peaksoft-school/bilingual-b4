package kg.peaksoft.bilingualb4.services;

import kg.peaksoft.bilingualb4.api.payload.UsersAnswerRequest;
import kg.peaksoft.bilingualb4.api.payload.UsersAnswerResponse;
import kg.peaksoft.bilingualb4.model.entity.UsersAnswer;

public interface UsersAnswerService {

    UsersAnswerResponse save(Long id,UsersAnswerRequest usersAnswerRequest);
}
