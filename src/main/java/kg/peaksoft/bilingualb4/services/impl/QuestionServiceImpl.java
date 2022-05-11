package kg.peaksoft.bilingualb4.services.impl;

import kg.peaksoft.bilingualb4.api.payload.QuestionRequest;
import kg.peaksoft.bilingualb4.api.payload.QuestionResponse;
import kg.peaksoft.bilingualb4.exception.BadRequestException;
import kg.peaksoft.bilingualb4.exception.NotFoundException;
import kg.peaksoft.bilingualb4.model.entity.Options;
import kg.peaksoft.bilingualb4.model.enums.SingleAndMultiType;
import kg.peaksoft.bilingualb4.model.mappers.QuestionMapper;
import kg.peaksoft.bilingualb4.model.entity.Question;
import kg.peaksoft.bilingualb4.model.enums.QuestionType;
import kg.peaksoft.bilingualb4.repository.QuestionRepository;
import kg.peaksoft.bilingualb4.services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;


@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Override
    public List<QuestionResponse> findAll(QuestionType questionType) {
        return questionMapper.mapToResponse(questionRepository.findAllByQuestionType(questionType));
    }

    @Override
    public QuestionResponse save(Long testId, QuestionRequest questionRequest) {
        System.out.println(questionRequest.getOptionsList().toString());
        System.out.println(questionRequest.getQuestionType());
        if (questionRequest.getOptionsList().isEmpty() && questionRequest.getQuestionType() == QuestionType.SELECT_REAL_ENGLISH_WORD ||
                questionRequest.getOptionsList().isEmpty() && questionRequest.getQuestionType() == QuestionType.LISTEN_AND_SELECT_WORD ||
                questionRequest.getOptionsList().isEmpty() && questionRequest.getQuestionType() == QuestionType.SELECT_MAIN_IDEA ||
                questionRequest.getOptionsList().isEmpty() && questionRequest.getQuestionType() == QuestionType.SELECT_THE_BEST_TITLE) {
            throw new BadRequestException("You should to choose at least one option!");
        }
        int counterOfCorrectOptions = 0;
        for (Options options : questionRequest.getOptionsList()) {
            if (options.isCorrectAnswer()) {
                counterOfCorrectOptions++;
            }
        }
        if (counterOfCorrectOptions > 1) {
            questionRequest.setSingleAndMultiType(SingleAndMultiType.MULTI);
        } else {
            questionRequest.setSingleAndMultiType(SingleAndMultiType.SINGLE);
        }
        Question question = questionMapper.mapToEntity(null, testId, questionRequest);
        Question save = questionRepository.save(question);
        return questionMapper.mapToResponse(save);
    }

    @Override
    public QuestionResponse findByIdAndName(Long id, String name) {
        int counter = id != null ? 1 : 0;
        counter += !isNullOrEmpty(name) ? 1 : 0;
        if (counter > 1) {
            throw new BadRequestException(
                    "You should to choose  only one field"
            );
        }
        if (id != null) {
            return findById(id);
        }
        if (!isNullOrEmpty(name)) {
            Question question = questionRepository.findByName(name).orElseThrow(() -> new NotFoundException(
                    String.format("Question with name = %s does not exists", name)
            ));
            return questionMapper.mapToResponse(question);
        }
        throw new BadRequestException("You should write one of {id, name} to get Type");
    }

    @Override
    public QuestionResponse deleteById(Long id) {
        QuestionResponse response = findById(id);
        boolean exists = questionRepository.existsById(id);
        if (!exists) {
            throw new BadRequestException(
                    String.format(
                            "Question with id %s does not exists", id
                    )
            );
        }
        questionRepository.deleteById(id);
        return response;
    }

    @Override
    public QuestionResponse updateById(Long id, QuestionRequest questionRequest) {
        Question question = questionRepository.getById(id);
        boolean exists = questionRepository.existsById(id);
        Question response;
        if (!exists) {
            throw new BadRequestException(
                    String.format("question with %d is already exists", id)
            );
        } else {
            response = questionMapper.mapToEntity(id, question.getTest().getId(), questionRequest);
            questionRepository.save(response);
        }
        return questionMapper.mapToResponse(response);
    }

    private QuestionResponse findById(Long id) {
        return questionMapper.mapToResponse(questionRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Question with id = %s does not exists", id)
        )));
    }
}
