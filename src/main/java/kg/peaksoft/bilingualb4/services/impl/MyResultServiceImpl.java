package kg.peaksoft.bilingualb4.services.impl;

import kg.peaksoft.bilingualb4.api.payload.MyResultRequest;
import kg.peaksoft.bilingualb4.api.payload.MyResultResponse;
import kg.peaksoft.bilingualb4.api.payload.UsersAnswerResponse;
import kg.peaksoft.bilingualb4.model.entity.MyResult;
import kg.peaksoft.bilingualb4.model.entity.UsersAnswer;
import kg.peaksoft.bilingualb4.model.enums.Status;
import kg.peaksoft.bilingualb4.model.mappers.MyResultMapper;
import kg.peaksoft.bilingualb4.repository.MyResultRepository;
import kg.peaksoft.bilingualb4.repository.UsersAnswerRepository;
import kg.peaksoft.bilingualb4.services.MyResultService;
import kg.peaksoft.bilingualb4.services.UsersAnswerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MyResultServiceImpl implements MyResultService {

    private final UsersAnswerRepository usersAnswerRepository;
    private final MyResultRepository resultRepository;
    private final MyResultMapper myResultMapper;

    @Override
    public MyResultResponse saveUserResult(Long id) {
        List<UsersAnswer> usersAnswerList = usersAnswerRepository.findAllByTestId(id);
        MyResultRequest resultRequest = new MyResultRequest();
        for (UsersAnswer usersAnswer: usersAnswerList){
            resultRequest.setTestName(usersAnswer.getQuestion().getTest().getTitle());
        }
        resultRequest.setDateOfSubmission(LocalDateTime.now());
        resultRequest.setStatus(Status.NOT_EVALUATE);
        resultRequest.setScore(0);
        MyResult result = resultRepository.save(myResultMapper.mapToEntity(resultRequest));
        return myResultMapper.mapToResponse(result);
    }

    @Override
    public MyResultResponse deleteUserResultById(Long id) {
        MyResult myResult = resultRepository.findById(id).get();
        resultRepository.deleteById(id);
        return myResultMapper.mapToResponse(myResult);
    }

    @Override
    public List<MyResultResponse> findAll() {
        List<MyResult>myResults = resultRepository.findAll();
        return myResultMapper.mapToResponse(myResults);
    }
}
