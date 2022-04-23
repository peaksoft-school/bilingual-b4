package kg.peaksoft.bilingualb4.services.impl;

import kg.peaksoft.bilingualb4.api.payload.QuestionRequest;
import kg.peaksoft.bilingualb4.api.payload.QuestionResponse;
import kg.peaksoft.bilingualb4.exception.BadRequestException;
import kg.peaksoft.bilingualb4.exception.NotFoundException;
import kg.peaksoft.bilingualb4.model.mappers.editMapper.QuestionEditMapper;
import kg.peaksoft.bilingualb4.model.mappers.viewMapper.QuestionViewMapper;
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
    private final QuestionEditMapper questionEditMapper;
    private final QuestionViewMapper questionViewMapper;

    @Override
    public List<Question> findAll(QuestionType questionType) {
        return questionRepository.findAllByQuestionType(questionType);
    }

    @Override
    public QuestionResponse save(Long id, QuestionRequest questionRequest) {
        Question question = questionEditMapper.create(id, questionRequest);
        Question save = questionRepository.save(question);
        return questionViewMapper.view(save);
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
            Question question = findById(id);
            return questionViewMapper.view(question);
        }
        if (!isNullOrEmpty(name)) {
            Question question = questionRepository.findByName(name).orElseThrow(() -> new NotFoundException(
                    String.format("Type with name = %s does not exists", name)
            ));
            return questionViewMapper.view(question);
        }
        throw new BadRequestException("You should write one of {id, name} to get Type");
    }

    @Override
    public void deleteById(Long id) {
        boolean exists = questionRepository.existsById(id);
        if (!exists) {
            throw new BadRequestException(
                    String.format(
                            "Type with id %s does not exists", id
                    )
            );
        }
        questionRepository.deleteById(id);
    }

    @Override
    public Question updateById(Long id, QuestionRequest questionRequest) {
        Question question = findById(id);

        boolean exists = questionRepository.existsById(id);

        if (exists) {
            throw new BadRequestException(
                    String.format("question with %d is already exists", id)
            );
        } else {
            question.setName(questionRequest.getName());
            question.setSingleAndMultiType(questionRequest.getSingleAndMultiType());
            question.setOptionList(questionRequest.getOptionList());
            question.setAudio(questionRequest.getAudio());
            question.setNumberOfReplays(questionRequest.getNumberOfReplays());
            question.setUpload(questionRequest.getUpload());
            question.setPlay(questionRequest.getPlay());
            question.setCorrectAnswer(questionRequest.isCorrectAnswer());
            question.setRecord(questionRequest.getRecord());
            question.setUploadImage(questionRequest.getUploadImage());
            question.setQuestionStatement(questionRequest.getQuestionStatement());
            question.setWordCounter(questionRequest.getWordCounter());
            question.setQuestionToThePassage(questionRequest.getQuestionToThePassage());
            question.setPassage(questionRequest.getPassage());
            question.setHighlightCorrectAnswer(questionRequest.getHighlightCorrectAnswer());

        }
        return question;
    }

    private Question findById(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Type with id = %s does not exists", id)
        ));
    }
}
