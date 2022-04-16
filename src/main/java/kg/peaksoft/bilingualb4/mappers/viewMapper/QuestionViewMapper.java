package kg.peaksoft.bilingualb4.mappers.viewMapper;

import kg.peaksoft.bilingualb4.dto.response.QuestionResponse;
import kg.peaksoft.bilingualb4.models.Question;
import org.springframework.stereotype.Component;

@Component
public class QuestionViewMapper {

    public QuestionResponse view(Question question){
        if (question ==null){
            return null;
        }
        QuestionResponse questionResponse = new QuestionResponse();
        if (question.getId() != null){
            questionResponse.setId(String.valueOf(question.getId()));
        }
        questionResponse.setName(question.getName());
        questionResponse.setSingleAndMultiType(question.getSingleAndMultiType());
        questionResponse.setWordList(question.getWordList());
        questionResponse.setAudio(question.getAudio());
        questionResponse.setNumberOfReplays(question.getNumberOfReplays());
        questionResponse.setUpload(question.getUpload());
        questionResponse.setPlay(question.getPlay());
        questionResponse.setCorrectAnswer(question.isCorrectAnswer());
        questionResponse.setRecord(question.getRecord());
        questionResponse.setUploadImage(question.getUploadImage());
        questionResponse.setQuestionStatement(question.getQuestionStatement());
        questionResponse.setWordCounter(question.getWordCounter());
        questionResponse.setQuestionToThePassage(question.getQuestionToThePassage());
        questionResponse.setPassage(question.getPassage());
        questionResponse.setHighlightCorrectAnswer(question.getHighlightCorrectAnswer());
        questionResponse.setQuestionType(question.getQuestionType());
        return questionResponse;
    }
}
