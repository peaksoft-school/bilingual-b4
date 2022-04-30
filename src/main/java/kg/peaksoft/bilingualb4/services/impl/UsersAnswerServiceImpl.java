package kg.peaksoft.bilingualb4.services.impl;

import kg.peaksoft.bilingualb4.api.payload.UsersAnswerRequest;
import kg.peaksoft.bilingualb4.api.payload.UsersAnswerResponse;
import kg.peaksoft.bilingualb4.model.entity.*;
import kg.peaksoft.bilingualb4.model.enums.Status;
import kg.peaksoft.bilingualb4.model.mappers.UsersAnswerMapper;
import kg.peaksoft.bilingualb4.repository.*;
import kg.peaksoft.bilingualb4.services.UsersAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersAnswerServiceImpl implements UsersAnswerService {

    private final UsersAnswerRepository usersAnswerRepository;
    private final UsersAnswerMapper usersAnswerEditMapper;
    private final TestResultRepository testResultRepository;
    private final UserRepository userRepository;
    private final MyResultRepository myResultRepository;
    private final QuestionResultRepository questionResultRepository;

    @Override
    public UsersAnswerResponse save(Long questionId, UsersAnswerRequest usersAnswerRequest, UserDetails userDetails) {
        UsersAnswer usersAnswer = usersAnswerEditMapper.mapToEntity(null,questionId,usersAnswerRequest);
        UsersAnswer save = usersAnswerRepository.save(usersAnswer);

        User user = userRepository.findByEmail(userDetails.getUsername());
        TestResult testResult = new TestResult();
        testResult.setUserName(user.getUserName());
        testResult.setDateOfSubmission(LocalDateTime.now());
        testResult.setTestName(usersAnswer.getQuestion().getTest().getTitle());
        testResult.setStatus(Status.NOT_EVALUATE);
        testResult.setScore(0);
        testResultRepository.save(testResult);

        MyResult myResult = new MyResult();
        myResult.setDateOfSubmission(LocalDateTime.now());
        myResult.setTestName(usersAnswer.getQuestion().getTest().getTitle());
        myResult.setStatus(Status.NOT_EVALUATE);
        myResult.setScore(0);
        myResult.setUser(user);
        myResultRepository.save(myResult);

        QuestionResult questionResult = new QuestionResult();
        questionResult.setUserName(user.getUserName());
        questionResult.setTestName(usersAnswer.getQuestion().getTest().getTitle());
        questionResult.setDateOfSubmission(LocalDateTime.now());
        questionResult.setQuestionName(usersAnswer.getQuestion().getName());
        questionResult.setScore(0);
        questionResult.setStatus(Status.NOT_EVALUATE);
        questionResult.setFinalScore(myResult.getScore());
        questionResult.setFinalStatus(myResult.getStatus());
        questionResult.setTestResult(testResult);
        questionResultRepository.save(questionResult);

        return usersAnswerEditMapper.mepToResponse(save);
    }

    @Override
    public void toCancel(Long id) {
       List<UsersAnswer> usersAnswerList =  usersAnswerRepository.findAllByTestId(id);
        usersAnswerRepository.deleteAll(usersAnswerList);
    }
}
