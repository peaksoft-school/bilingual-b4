package kg.peaksoft.bilingualb4.model.mappers.editMapper;

import kg.peaksoft.bilingualb4.api.payload.QuestionRequest;
import kg.peaksoft.bilingualb4.model.entity.Question;
import kg.peaksoft.bilingualb4.repository.TestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class QuestionEditMapper {

    private final TestRepository testRepository;

    public Question create(Long id, QuestionRequest questionRequest) {
        if (questionRequest == null) {
            return null;
        }
        Question question = new Question();
        return mapToEntity(id, questionRequest, question);
    }

    private Question mapToEntity(Long id, QuestionRequest questionRequest, Question question) {
        return Question.builder()
                .name(questionRequest.getName())
                .singleAndMultiType(questionRequest.getSingleAndMultiType())
                .wordList(questionRequest.getWordList())
                .audio(questionRequest.getAudio())
                .numberOfReplays(questionRequest.getNumberOfReplays())
                .upload(questionRequest.getUpload())
                .play(questionRequest.getPlay())
                .correctAnswer(questionRequest.isCorrectAnswer())
                .record(questionRequest.getRecord())
                .uploadImage(questionRequest.getUploadImage())
                .questionStatement(questionRequest.getQuestionStatement())
                .wordCounter(questionRequest.getWordCounter())
                .questionToThePassage(questionRequest.getQuestionToThePassage())
                .passage(questionRequest.getPassage())
                .highlightCorrectAnswer(questionRequest.getHighlightCorrectAnswer())
                .questionType(questionRequest.getQuestionType())
                .test(testRepository.findById(id).get())
                .build();
    }
}