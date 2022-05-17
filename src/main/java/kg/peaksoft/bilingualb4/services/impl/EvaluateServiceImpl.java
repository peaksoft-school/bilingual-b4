package kg.peaksoft.bilingualb4.services.impl;

import kg.peaksoft.bilingualb4.api.payload.EvaluateResponse;
import kg.peaksoft.bilingualb4.api.payload.QuestionResultRequest;
import kg.peaksoft.bilingualb4.api.payload.UsersAnswerResponse;
import kg.peaksoft.bilingualb4.exception.NotFoundException;
import kg.peaksoft.bilingualb4.model.entity.*;
import kg.peaksoft.bilingualb4.model.enums.Status;
import kg.peaksoft.bilingualb4.model.mappers.UsersAnswerMapper;
import kg.peaksoft.bilingualb4.repository.MyResultRepository;
import kg.peaksoft.bilingualb4.repository.QuestionResultRepository;
import kg.peaksoft.bilingualb4.repository.UsersAnswerRepository;
import kg.peaksoft.bilingualb4.services.EvaluateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static kg.peaksoft.bilingualb4.model.enums.Status.EVALUATE;

@Service
@AllArgsConstructor
@Slf4j
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
        }
        for (Options options : evaluate.getOptions()) {
            if (options.isCorrect()) {
                correctOption++;
            }
        }

        List<UsersAnswer> usersAnswerList;
        for (User user : test.getUserList()) {
            evaluate.setUserName(user.getUserName());
            UsersAnswer usersAnswer = usersAnswerRepository.findById(userAnswerId).orElseThrow(() ->
                    new NotFoundException(String.format("Object 'user_answer' with %d id not found!", userAnswerId)));
            exist = myResultRepository.existsByUserId(user.getId());
            usersAnswerList = new ArrayList<>();
            usersAnswerList.add(usersAnswer);
            evaluate.setUserAnswer(usersAnswerMapper.mapToResponse(usersAnswerList));
        }

        for (int i = 0; i < evaluate.getUserAnswer().size(); i++) {
            for (int j = 0; j < evaluate.getUserAnswer().get(i).getOptionsList().size(); j++) {
                if (evaluate.getUserAnswer().get(i).getOptionsList().get(j).isCorrect()) {
                    allUserCorrectAnswer++;
                    if (evaluate.getUserAnswer().get(i).getOptionsList().get(j).isCorrect() == evaluate.getOptions().get(j).isCorrect()) {
                        userCorrectAnswer++;
                    }
                }
            }
        }

        int finalScore = 0;
        if (allUserCorrectAnswer > correctOption) {
            evaluate.setScore(0);
        } else {
            int score = (((userCorrectAnswer * 100) / correctOption) / 10);
            evaluate.setScore(Math.round(score));
            finalScore += score;
        }

        questionResult.setScore(evaluate.getScore());
        questionResult.setStatus(EVALUATE);
        questionResult.setFinalScore(finalScore);
        if (questionResult.getFinalScore() != 0) {
            questionResult.setFinalStatus(EVALUATE);
        }
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
            if (questionResult1.getStatus() == EVALUATE) {
                counterForFinalStatus++;
            }
            sum += questionResult1.getScore();
        }
        questionResult.setFinalScore(sum);
        if (counterForFinalStatus == test.getQuestionList().size()) {
            questionResult.setFinalStatus(EVALUATE);
        }

        myResult.setStatus(questionResult.getFinalStatus());
        myResult.setScore(questionResult.getFinalScore());
        myResultRepository.save(myResult);
        questionResultRepository.save(questionResult);
        return evaluate;
    }

    @Override
    public EvaluateResponse manualChek(Long questionId, Long userAnswerId, QuestionResultRequest questionResultRequest) {
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
        }
        int sum = 0;
        Long userId = null;
        boolean exist = false;
        int counterForFinalStatus = 0;

        for (User user : test.getUserList()) {
            evaluate.setUserName(user.getUserName());
            UsersAnswer usersAnswer = usersAnswerRepository.findById(userAnswerId).orElseThrow(() ->
                    new NotFoundException(String.format("Object 'user_answer' with %d id not found!", userAnswerId)));
            exist = myResultRepository.existsByUserId(user.getId());
            List<UsersAnswer> usersAnswerList = new ArrayList<>(List.of(usersAnswer));
            evaluate.setUserAnswer(usersAnswerMapper.mapToResponse(usersAnswerList));
        }
        evaluate.setScore(questionResult.getScore());
        questionResult.setScore(evaluate.getScore());
        questionResult.setStatus(EVALUATE);
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
            questionResult.setFinalStatus(EVALUATE);
        }
        myResult.setStatus(questionResult.getFinalStatus());
        myResult.setScore(questionResult.getFinalScore());
        myResultRepository.save(myResult);
        questionResultRepository.save(questionResult);
        return evaluate;
    }

    @Override
    public EvaluateResponse methodForHighlightType(Long questionId, Long userAnswerId) {
        QuestionResult questionResult = questionResultRepository.findById(questionId).orElseThrow(() ->
                new NotFoundException(String.format("Entity 'Question result' with %d id not found!", questionId)));

        EvaluateResponse evaluate = new EvaluateResponse();
        Test test = questionResult.getQuestion().getTest();
        int sum = 0;
        Long userId = null;
        boolean exist = false;
        int wrongAnswer = 0;
        int userCorrectAnswer = 0;
        int counterForFinalStatus = 0;

        evaluate.setTestName(test.getTitle());
        for (Question question : test.getQuestionList()) {
            evaluate.setCorrectAnswer(question.getCorrectAnswer());
            evaluate.setQuestionName(question.getName());
            evaluate.setDuration(question.getDuration());
            evaluate.setQuestionType(question.getQuestionType());
        }

        for (User user : test.getUserList()) {
            evaluate.setUserName(user.getUserName());
            UsersAnswer usersAnswer = usersAnswerRepository.findById(userAnswerId).orElseThrow(() ->
                    new NotFoundException(String.format("Object 'user_answer' with %d id not found!", userAnswerId)));
            exist = myResultRepository.existsByUserId(user.getId());
            List<UsersAnswer> usersAnswerList = new ArrayList<>();
            usersAnswerList.add(usersAnswer);
            evaluate.setUserAnswer(usersAnswerMapper.mapToResponse(usersAnswerList));
        }

        String[] array1 = new String[0];
        String[] array = new String[0];

        for (int i = 0; i < evaluate.getUserAnswer().size(); i++) {
            array1 = evaluate.getCorrectAnswer().split(" ");
            array = evaluate.getUserAnswer().get(i).getSomeText().split(" ");
        }

        for (int i = 0; i < array.length; i++) {
            if (Objects.equals(array[i], array1[i])) {
                userCorrectAnswer++;
            } else {
                wrongAnswer++;
            }
        }
        int score = (userCorrectAnswer * 100 / (userCorrectAnswer + wrongAnswer) / 10);

        evaluate.setScore(Math.round(score));

        questionResult.setScore(evaluate.getScore());
        questionResult.setStatus(EVALUATE);
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
            if (questionResult1.getStatus() == EVALUATE) {
                counterForFinalStatus++;
            }
            sum += questionResult1.getScore();
        }
        questionResult.setFinalScore(sum);
        if (counterForFinalStatus == test.getQuestionList().size()) {
            questionResult.setFinalStatus(EVALUATE);
        }

        myResult.setStatus(questionResult.getFinalStatus());
        myResult.setScore(questionResult.getFinalScore());
        myResultRepository.save(myResult);
        questionResultRepository.save(questionResult);
        return evaluate;
    }
}


