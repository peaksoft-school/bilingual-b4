package kg.peaksoft.bilingualb4.services.impl;

import kg.peaksoft.bilingualb4.api.payload.EvaluateResponse;
import kg.peaksoft.bilingualb4.api.payload.QuestionResultRequest;
import kg.peaksoft.bilingualb4.api.payload.QuestionResultResponse;
import kg.peaksoft.bilingualb4.exception.BadRequestException;
import kg.peaksoft.bilingualb4.exception.NotFoundException;
import kg.peaksoft.bilingualb4.model.entity.MyResult;
import kg.peaksoft.bilingualb4.model.entity.QuestionResult;
import kg.peaksoft.bilingualb4.model.entity.User;
import kg.peaksoft.bilingualb4.model.entity.UsersAnswer;
import kg.peaksoft.bilingualb4.model.enums.Status;
import kg.peaksoft.bilingualb4.model.mappers.QuestionResultMapper;
import kg.peaksoft.bilingualb4.repository.MyResultRepository;
import kg.peaksoft.bilingualb4.repository.QuestionResultRepository;
import kg.peaksoft.bilingualb4.services.EvaluateService;
import kg.peaksoft.bilingualb4.services.QuestionResultService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
public class QuestionResultServiceImpl implements QuestionResultService {

    private final QuestionResultMapper questionResultMapper;
    private final QuestionResultRepository questionResultRepository;
    private final EvaluateService evaluateService;
    private final EmailService emailService;
    private final MyResultRepository myResultRepository;

    @Override
    public List<QuestionResultResponse> findAll() {
        List<QuestionResult> questionResultList = questionResultRepository.findAll();
        return questionResultMapper.mapToResponse(questionResultList);
    }

    @Override
    public QuestionResultResponse findById(Long id) {
        return questionResultMapper.mapToResponse(questionResultRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Object 'questionResult' with %d id not found!", id)
        )));
    }

    @Override
    public EvaluateResponse updateById(Long id, QuestionResultRequest questionResultRequest) {
        QuestionResult questionResult = questionResultRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Object 'questionResult' with %d id not found!", id)
        ));
        questionResult.setScore(questionResultRequest.getScore());
        Long userAnswerId = null;
        EvaluateResponse evaluateResponse;
        for (User user : questionResult.getQuestion().getTest().getUserList()) {
            for (UsersAnswer usersAnswer : user.getUsersAnswers()) {
                userAnswerId = usersAnswer.getId();
            }
        }
        evaluateResponse = evaluateService.manualChek(questionResult.getId(), userAnswerId, questionResultRequest);
        return evaluateResponse;
    }

    @Override
    public String sendResultsToUser(Long id) {
        MyResult myResult = myResultRepository.findById(id).orElseThrow(() ->
                new NotFoundException(
                        String.format("Result with %d id not found!", id)));

        String userEmail;
        if (myResult.getStatus() != Status.EVALUATE) {
            throw new BadRequestException("This user's answers are not yet fully verified");
        }else {
            userEmail = myResult.getUser().getEmail();

            String userName = myResult.getUser().getUserName().toUpperCase(Locale.ROOT);
            String testName = myResult.getTestName();
            LocalDateTime date = myResult.getDateOfSubmission();
            int score  = myResult.getScore();

            String message =
                    "\nDear " + userName +
                            ", \nYour test: "+testName+
                            ", \nYou finished at: " + date +
                            ", \nYour score: " + score;

            emailService.send(userEmail, message);
        }
        return String.format("Result successfully send to %s", userEmail);
    }
}
