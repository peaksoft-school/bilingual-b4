package kg.peaksoft.bilingualb4.services.impl;

import kg.peaksoft.bilingualb4.api.payload.EvaluateResponse;
import kg.peaksoft.bilingualb4.exception.NotFoundException;
import kg.peaksoft.bilingualb4.model.entity.*;
import kg.peaksoft.bilingualb4.model.enums.Status;
import kg.peaksoft.bilingualb4.model.mappers.UsersAnswerMapper;
import kg.peaksoft.bilingualb4.repository.MyResultRepository;
import kg.peaksoft.bilingualb4.repository.QuestionResultRepository;
import kg.peaksoft.bilingualb4.repository.UsersAnswerRepository;
import kg.peaksoft.bilingualb4.services.EvaluateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EvaluateServiceImpl implements EvaluateService {

    private final UsersAnswerRepository usersAnswerRepository;
    private final UsersAnswerMapper usersAnswerMapper;
    private final QuestionResultRepository questionResultRepository;
    private final MyResultRepository myResultRepository;

    @Override
    public EvaluateResponse autoCheck(Long questionId, Long userAnswerId) {
        QuestionResult questionResult = questionResultRepository.findById(questionId).orElseThrow(() ->
                new NotFoundException(String.format("Entity 'Question result' with %d id not found!", questionId)));

        EvaluateResponse evaluate = new EvaluateResponse();
        Test test = questionResult.getQuestion().getTest();

        int sum = 0;
        Long userId = null;
        boolean exist = false;
        int correctOption = 0;
        int userCorrectAnswer = 0;
        int allUserCorrectAnswer = 0;
        int counterForFinalStatus = 0;

        evaluate.setTestName(test.getTitle());
        for (Question question : test.getQuestionList()) {
            evaluate.setQuestionName(question.getName());
            evaluate.setDuration(question.getDuration());
            evaluate.setQuestionType(question.getQuestionType());
            evaluate.setOptions(question.getOptionsList());
            evaluate.setOptions(question.getOptionsList());
        }
        for (Options options : evaluate.getOptions()) {
            if (options.isCorrectAnswer()) {
                correctOption++;
            }
        }

        for (User user : test.getUserList()) {
            evaluate.setUserName(user.getUserName());
            UsersAnswer usersAnswer = usersAnswerRepository.findById(userAnswerId).orElseThrow(() ->
                    new NotFoundException(String.format("Object 'user_answer' with %d id not found!", userAnswerId)));
            exist = myResultRepository.existsByName(user.getId());
            List<UsersAnswer>usersAnswerList = new ArrayList<>();
            usersAnswerList.add(usersAnswer);
            evaluate.setUserAnswer(usersAnswerMapper.mapToResponse(usersAnswerList));
        }

        for (int i = 0; i < evaluate.getUserAnswer().size(); i++) {
            if (evaluate.getUserAnswer().get(i).getOptionsList().get(i).isCorrectAnswer()) {
                allUserCorrectAnswer++;
                if (evaluate.getUserAnswer().get(i).getOptionsList().get(i).isCorrectAnswer() == evaluate.getOptions().get(i).isCorrectAnswer()) {
                    userCorrectAnswer++;
                }
            }
        }

        if (allUserCorrectAnswer > correctOption) {
            evaluate.setScore(0);
        } else {
            int score = (((userCorrectAnswer * 100) / correctOption) / 10);
            evaluate.setScore(Math.round(score));
        }

        System.out.println(evaluate.getScore());
        questionResult.setScore(evaluate.getScore());
        questionResult.setStatus(Status.EVALUATE);
        questionResult.setFinalScore(0);
        questionResult.setFinalStatus(Status.NOT_EVALUATE);
        for (User user : test.getUserList()) {
            userId = user.getId();
        }

        MyResult myResult;
        if (!exist) {
            myResult = new MyResult();
            myResult.setDateOfSubmission(LocalDateTime.now());
            myResult.setTestName(test.getTitle());
            myResult.setStatus(questionResult.getFinalStatus());
            myResult.setScore(questionResult.getFinalScore());
            for (User user : test.getUserList()) {
                myResult.setUser(user);
            }
        } else {
            myResult = myResultRepository.findByUserId(userId);
            myResult.setScore(questionResult.getFinalScore());
            myResult.setStatus(questionResult.getFinalStatus());
        }
        questionResult.setMyResult(myResult);
        myResultRepository.save(myResult);

        List<QuestionResult> questionResultList = questionResultRepository.findAllByMyResultId(myResult.getId());
        for (QuestionResult questionResult1 : questionResultList) {
            if (questionResult1.getStatus() == Status.EVALUATE) {
                counterForFinalStatus++;
            }
            sum += questionResult1.getScore();
        }
        questionResult.setFinalScore(sum);
        if (counterForFinalStatus == test.getQuestionList().size()) {
            questionResult.setFinalStatus(Status.EVALUATE);
        }

        myResult.setStatus(questionResult.getFinalStatus());
        myResult.setScore(questionResult.getFinalScore());
        myResultRepository.save(myResult);
        questionResultRepository.save(questionResult);
        return evaluate;
    }

    @Override
    public EvaluateResponse manualChek(Long questionId, Long userAnswerId, QuestionResult questionResultRequest) {
        QuestionResult questionResult = questionResultRepository.findById(questionId).orElseThrow(() ->
                new NotFoundException(String.format("Entity 'Question result' with %d id not found!", questionId)));

        EvaluateResponse evaluate = new EvaluateResponse();
        Test test = questionResult.getQuestion().getTest();
        evaluate.setTestName(test.getTitle());
        for (Question question : test.getQuestionList()) {
            evaluate.setQuestionName(question.getName());
            evaluate.setDuration(question.getDuration());
            evaluate.setQuestionType(question.getQuestionType());
            evaluate.setOptions(question.getOptionsList());
            evaluate.setOptions(question.getOptionsList());
        }
        System.out.println("THird check");
        int sum = 0;
        Long userId = null;
        boolean exist = false;
        int counterForFinalStatus = 0;

        for (User user : test.getUserList() ) {
            evaluate.setUserName(user.getUserName());
            UsersAnswer usersAnswer = usersAnswerRepository.findById(userAnswerId).orElseThrow(() ->
                    new NotFoundException(String.format("Object 'user_answer' with %d id not found!", userAnswerId)));
            exist = myResultRepository.existsByName(user.getId());
            List<UsersAnswer>usersAnswerList = new ArrayList<>(List.of(usersAnswer));
            evaluate.setUserAnswer(usersAnswerMapper.mapToResponse(usersAnswerList));
        }
        evaluate.setScore(questionResult.getScore());
        questionResult.setScore(evaluate.getScore());
        questionResult.setStatus(Status.EVALUATE);
        questionResult.setFinalScore(0);
        questionResult.setFinalStatus(Status.NOT_EVALUATE);
        for (User user : test.getUserList()) {
            userId = user.getId();
        }
        MyResult myResult;
        if (!exist) {
            myResult = new MyResult();
            myResult.setDateOfSubmission(LocalDateTime.now());
            myResult.setTestName(test.getTitle());
            myResult.setStatus(questionResult.getFinalStatus());
            myResult.setScore(questionResult.getFinalScore());
            for (User user : test.getUserList()) {
                myResult.setUser(user);
            }
        } else {
            myResult = myResultRepository.findByUserId(userId);
            myResult.setScore(questionResult.getFinalScore());
            myResult.setStatus(questionResult.getFinalStatus());
        }
        questionResult.setMyResult(myResult);
        myResultRepository.save(myResult);

        List<QuestionResult> questionResultList = questionResultRepository.findAllByMyResultId(myResult.getId());
        for (QuestionResult questionResult1 : questionResultList) {

            sum += questionResult1.getScore();
        }
        questionResult.setFinalScore(sum);
        if (counterForFinalStatus == test.getQuestionList().size()) {
            questionResult.setFinalStatus(Status.EVALUATE);
        }
        myResult.setStatus(questionResult.getFinalStatus());
        myResult.setScore(questionResult.getFinalScore());
        myResultRepository.save(myResult);
        questionResultRepository.save(questionResult);
        return evaluate;
    }

    @Override
    public EvaluateResponse methodForHighlightType(Long questionId, Long userAnswerId) {

        return null;
    }
}


