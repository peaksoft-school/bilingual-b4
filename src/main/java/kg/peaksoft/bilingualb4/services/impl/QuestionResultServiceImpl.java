package kg.peaksoft.bilingualb4.services.impl;

import kg.peaksoft.bilingualb4.api.payload.EvaluateResponse;
import kg.peaksoft.bilingualb4.api.payload.QuestionResultRequest;
import kg.peaksoft.bilingualb4.api.payload.QuestionResultResponse;
import kg.peaksoft.bilingualb4.exception.NotFoundException;
import kg.peaksoft.bilingualb4.model.entity.QuestionResult;
import kg.peaksoft.bilingualb4.model.entity.User;
import kg.peaksoft.bilingualb4.model.entity.UsersAnswer;
import kg.peaksoft.bilingualb4.model.mappers.QuestionResultMapper;
import kg.peaksoft.bilingualb4.repository.QuestionResultRepository;
import kg.peaksoft.bilingualb4.services.EvaluateService;
import kg.peaksoft.bilingualb4.services.QuestionResultService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class QuestionResultServiceImpl implements QuestionResultService {

    private final QuestionResultMapper questionResultMapper;
    private final QuestionResultRepository questionResultRepository;
    private final EvaluateService evaluateService;

    @Override
    public List<QuestionResultResponse> findAll() {
        List<QuestionResult> questionResultList = questionResultRepository.findAll();
        log.info("Successful find all !");
        return questionResultMapper.mapToResponse(questionResultList);
    }

    @Override
    public QuestionResultResponse findById(Long id) {
        log.info("Successful find by id {} :",id);
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
        log.info("Successful update!");
        return evaluateResponse;
    }
}
