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
        return questionMethod(questionRequest, question);
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
        return questionMethod(questionRequest, question);
    }


    public static Question questionMethod(QuestionRequest questionRequest, Question question) {
        question.setName(questionRequest.getName());
        question.setSingleAndMultiType(questionRequest.getSingleAndMultiType());
        question.setWordList(questionRequest.getWordList());
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
        return question;
    }
}