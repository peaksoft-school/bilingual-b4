package kg.peaksoft.bilingualb4.services.impl;

import kg.peaksoft.bilingualb4.api.payload.UsersAnswerRequest;
import kg.peaksoft.bilingualb4.api.payload.UsersAnswerResponse;
import kg.peaksoft.bilingualb4.exception.NotFoundException;
import kg.peaksoft.bilingualb4.model.entity.QuestionResult;
import kg.peaksoft.bilingualb4.model.entity.Test;
import kg.peaksoft.bilingualb4.model.entity.User;
import kg.peaksoft.bilingualb4.model.entity.UsersAnswer;
import kg.peaksoft.bilingualb4.model.enums.Status;
import kg.peaksoft.bilingualb4.model.mappers.UsersAnswerMapper;
import kg.peaksoft.bilingualb4.repository.*;
import kg.peaksoft.bilingualb4.services.UsersAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersAnswerServiceImpl implements UsersAnswerService {

    private final UsersAnswerRepository usersAnswerRepository;
    private final UsersAnswerMapper usersAnswerEditMapper;
    private final UserRepository userRepository;
    private final TestRepository testRepository;
    private final QuestionRepository questionRepository;
    private final QuestionResultRepository questionResultRepository;

    @Override
    public UsersAnswerResponse save(Long questionId, UsersAnswerRequest usersAnswerRequest, UserDetails userDetails) {

        UsersAnswer usersAnswer = usersAnswerEditMapper.mapToEntity(null,questionId,usersAnswerRequest);
        User user1 = userRepository.findByEmail(userDetails.getUsername());
        List<UsersAnswer>usersAnswers = new ArrayList<>();
        usersAnswers.add(usersAnswer);
        user1.setUsersAnswers(usersAnswers);
        usersAnswer.setUser(user1);
        usersAnswerRepository.save(usersAnswer);

        Test test = usersAnswer.getQuestion().getTest();
        test.setUser(userRepository.findByEmail(userDetails.getUsername()));
        System.out.println(test);
        testRepository.save(test);

        User user = userRepository.findByEmail(userDetails.getUsername());

        QuestionResult questionResult = new QuestionResult();
        questionResult.setUserName(user.getUserName());
        questionResult.setTestName(usersAnswer.getQuestion().getTest().getTitle());
        questionResult.setDateOfSubmission(LocalDateTime.now());
        questionResult.setQuestionName(usersAnswer.getQuestion().getName());
        questionResult.setScore(0);
        questionResult.setStatus(Status.NOT_EVALUATE);
        questionResult.setQuestion(questionRepository.findById(questionId).
                orElseThrow(()->new NotFoundException(
                String.format("QuestionResult with %d id not found!", questionId))));
        questionResultRepository.save(questionResult);

        return usersAnswerEditMapper.mapToResponse(usersAnswer);
    }

    @Override
    public void toCancel(Long id) {
       List<UsersAnswer> usersAnswerList =  usersAnswerRepository.findAllByTestId(id);
        usersAnswerRepository.deleteAll(usersAnswerList);
    }
}
