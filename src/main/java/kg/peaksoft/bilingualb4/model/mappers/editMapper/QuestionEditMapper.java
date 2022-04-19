package kg.peaksoft.bilingualb4.model.mappers.editMapper;

import kg.peaksoft.bilingualb4.api.payload.QuestionRequest;
import kg.peaksoft.bilingualb4.api.payload.QuestionResponse;
import kg.peaksoft.bilingualb4.model.entity.Question;
import kg.peaksoft.bilingualb4.model.entity.Word;
import org.springframework.stereotype.Component;

@Component
public class QuestionEditMapper {

    public Question create(QuestionRequest questionRequest) {
        if (questionRequest == null) {
            return null;
        }
        Question question = new Question();
        return mapToEntity(questionRequest, question);
    }

    public Question update(Question question, QuestionRequest questionRequest) {
        question.setName(questionRequest.getName());
        question.setSingleAndMultiType(questionRequest.getSingleAndMultiType());

        for (Word word : questionRequest.getWordList()) {
            if (word.getId() != null) {
                for (Word newWord : question.getWordList()) {
                    newWord.setId(word.getId());
                }
            }
        }
        return mapToEntity(questionRequest, question);
    }


    private Question mapToEntity(QuestionRequest questionRequest, Question question) {
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
                .build();
    }
}