package kg.peaksoft.bilingualb4.services.impl;

import kg.peaksoft.bilingualb4.api.payload.UsersAnswerRequest;
import kg.peaksoft.bilingualb4.api.payload.UsersAnswerResponse;
import kg.peaksoft.bilingualb4.model.entity.UsersAnswer;
import kg.peaksoft.bilingualb4.model.mappers.editMapper.UsersAnswerEditMapper;
import kg.peaksoft.bilingualb4.model.mappers.viewMapper.UsersAnswerViewMapper;
import kg.peaksoft.bilingualb4.repository.UsersAnswerRepository;
import kg.peaksoft.bilingualb4.services.UsersAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersAnswerServiceImpl implements UsersAnswerService {

    private final UsersAnswerRepository usersAnswerRepository;
    private final UsersAnswerEditMapper usersAnswerEditMapper;
    private final UsersAnswerViewMapper usersAnswerViewMapper;

    @Override
    public UsersAnswerResponse save(Long id,UsersAnswerRequest usersAnswerRequest) {
        return usersAnswerViewMapper.view(usersAnswerRepository.save(usersAnswerEditMapper.create(id,usersAnswerRequest)));
    }
}
