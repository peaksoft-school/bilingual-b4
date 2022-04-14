package kg.peaksoft.bilingualb4.mappers.editMapper;

import kg.peaksoft.bilingualb4.dto.request.TypeRequest;
import kg.peaksoft.bilingualb4.dto.response.TypeResponse;
import kg.peaksoft.bilingualb4.models.Type;
import org.springframework.stereotype.Component;

@Component
public class TypeEditMapper {

    public Type create(TypeRequest typeRequest){
        if(typeRequest == null){
            return null;
        }
        Type type = new Type();
        type.setName(typeRequest.getName());
        type.setSingleAndMultiType(typeRequest.getSingleAndMultiType());
        type.setWordList(typeRequest.getWordList());
        type.setAudio(typeRequest.getAudio());
        type.setNumberOfReplays(typeRequest.getNumberOfReplays());
        type.setUpload(typeRequest.getUpload());
        type.setPlay(typeRequest.getPlay());
        type.setCorrectAnswer(typeRequest.isCorrectAnswer());
        type.setRecord(typeRequest.getRecord());
        type.setUploadImage(typeRequest.getUploadImage());
        type.setQuestionStatement(typeRequest.getQuestionStatement());
        type.setWordCounter(typeRequest.getWordCounter());
        type.setQuestionToThePassage(typeRequest.getQuestionToThePassage());
        type.setPassage(typeRequest.getPassage());
        type.setHighlightCorrectAnswer(typeRequest.getHighlightCorrectAnswer());
        return type;
    }

    public TypeResponse update(Type type, TypeRequest typeRequest){
        type.setName(typeRequest.getName());
        type.setSingleAndMultiType(typeRequest.getSingleAndMultiType());
        type.setWordList(typeRequest.getWordList());
        type.setAudio(typeRequest.getAudio());
        type.setNumberOfReplays(typeRequest.getNumberOfReplays());
        type.setUpload(typeRequest.getUpload());
        type.setPlay(typeRequest.getPlay());
        type.setCorrectAnswer(typeRequest.isCorrectAnswer());
        type.setRecord(typeRequest.getRecord());
        type.setUploadImage(typeRequest.getUploadImage());
        type.setQuestionStatement(typeRequest.getQuestionStatement());
        type.setWordCounter(typeRequest.getWordCounter());
        type.setQuestionToThePassage(typeRequest.getQuestionToThePassage());
        type.setPassage(typeRequest.getPassage());
        type.setHighlightCorrectAnswer(typeRequest.getHighlightCorrectAnswer());
        return null;
    }
}
