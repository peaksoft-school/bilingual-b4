package kg.peaksoft.bilingualb4.services.impl;

import kg.peaksoft.bilingualb4.api.payload.UsersAnswerRequest;
import kg.peaksoft.bilingualb4.api.payload.UsersAnswerResponse;
import kg.peaksoft.bilingualb4.model.entity.UsersAnswer;
import kg.peaksoft.bilingualb4.model.mappers.UsersAnswerMapper;
import kg.peaksoft.bilingualb4.repository.UsersAnswerRepository;
import kg.peaksoft.bilingualb4.services.UsersAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersAnswerServiceImpl implements UsersAnswerService {

    private final UsersAnswerRepository usersAnswerRepository;
    private final UsersAnswerMapper usersAnswerEditMapper;

    @Override
    public UsersAnswerResponse save(Long questionId,UsersAnswerRequest usersAnswerRequest) {
        UsersAnswer usersAnswer = usersAnswerEditMapper.mapToEntity(null,questionId,usersAnswerRequest);
        UsersAnswer save = usersAnswerRepository.save(usersAnswer);
        return usersAnswerEditMapper.mepToResponse(save);
    }

    @Override
    public void toCancel(Long id) {
       List<UsersAnswer> usersAnswerList =  usersAnswerRepository.findAllByTestId(id);
        usersAnswerRepository.deleteAll(usersAnswerList);
    }
}
