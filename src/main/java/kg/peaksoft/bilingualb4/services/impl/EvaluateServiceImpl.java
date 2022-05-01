package kg.peaksoft.bilingualb4.services.impl;

import kg.peaksoft.bilingualb4.api.payload.EvaluateResponse;
import kg.peaksoft.bilingualb4.exception.NotFoundException;
import kg.peaksoft.bilingualb4.model.entity.*;
import kg.peaksoft.bilingualb4.model.enums.Status;
import kg.peaksoft.bilingualb4.repository.QuestionResultRepository;
import kg.peaksoft.bilingualb4.repository.TestRepository;
import kg.peaksoft.bilingualb4.repository.UsersAnswerRepository;
import kg.peaksoft.bilingualb4.services.EvaluateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EvaluateServiceImpl implements EvaluateService {

    private final TestRepository testRepository;
    private final UsersAnswerRepository usersAnswerRepository;
    private final QuestionResultRepository questionResultRepository;

    @Override
    public EvaluateResponse save(Long id) {
        QuestionResult questionResult = questionResultRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Entity 'Question result' with %d id not found!", id)));

        EvaluateResponse evaluate = new EvaluateResponse();
        Test test = testRepository.findByTitle(questionResult.getTestName());
        for (User user : test.getUserList()) {
            assert false;
            evaluate.setUserName(user.getUserName());
        }
        int counter = 0, k = 0;
        assert false;
        evaluate.setTestName(test.getTitle());
        for (Question question : test.getQuestionList()) {
            evaluate.setQuestionName(question.getName());
            evaluate.setDuration(question.getDuration());
            evaluate.setQuestionType(question.getQuestionType());
            evaluate.setOptions(question.getOptionsList());
            evaluate.setOptions(question.getOptionsList());
            for (Options options : evaluate.getOptions()) {
                if (options.isCorrectAnswer()) {
                    counter++;
                }
            }
        }
        UsersAnswer usersAnswer = usersAnswerRepository.findByTestId(test.getId());
        evaluate.setUserAnswer(usersAnswer.getOptionsList());

        for (Options options : usersAnswer.getOptionsList()) {
            if (options.isCorrectAnswer()) {
                k++;
            }
        }
        if (counter == k) {
            evaluate.setScore(10);
        } else if (counter > k) {
            evaluate.setScore(0);
        }

        questionResult.setScore(evaluate.getScore());
        questionResult.setStatus(Status.EVALUATE);

        questionResultRepository.save(questionResult);
        return evaluate;
    }
}


