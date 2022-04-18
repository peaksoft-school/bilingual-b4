package kg.peaksoft.bilingualb4.model.mappers.viewMapper;

import kg.peaksoft.bilingualb4.api.payload.QuestionResponse;
import kg.peaksoft.bilingualb4.model.entity.Question;
import org.springframework.stereotype.Component;

@Component
public class QuestionViewMapper {

    public QuestionResponse view(Question question){
        if (question ==null){
            return null;
        }
        return QuestionResponse.builder()
                .id(String.valueOf(question.getId()))
                .name(question.getName())
                .singleAndMultiType(question.getSingleAndMultiType())
                .wordList(question.getWordList())
                .audio(question.getAudio())
                .numberOfReplays(question.getNumberOfReplays())
                .upload(question.getUpload())
                .play(question.getPlay())
                .correctAnswer(question.isCorrectAnswer())
                .record(question.getRecord())
                .uploadImage(question.getUploadImage())
                .questionStatement(question.getQuestionStatement())
                .wordCounter(question.getWordCounter())
                .questionToThePassage(question.getQuestionToThePassage())
                .passage(question.getPassage())
                .highlightCorrectAnswer(question.getHighlightCorrectAnswer())
                .questionType(question.getQuestionType())
                .build();
    }
}
