package kg.peaksoft.bilingualb4.mappers.viewMapper;

import kg.peaksoft.bilingualb4.dto.response.TypeResponse;
import kg.peaksoft.bilingualb4.models.Type;
import org.springframework.stereotype.Component;

@Component
public class TypeViewMapper {

    public TypeResponse view(Type type){
        if (type ==null){
            return null;
        }
        TypeResponse typeResponse = new TypeResponse();
        if (type.getId() != null){
            typeResponse.setId(String.valueOf(type.getId()));
        }
        typeResponse.setName(type.getName());
        typeResponse.setSingleAndMultiType(type.getSingleAndMultiType());
        typeResponse.setWordList(type.getWordList());
        typeResponse.setAudio(type.getAudio());
        typeResponse.setNumberOfReplays(type.getNumberOfReplays());
        typeResponse.setUpload(type.getUpload());
        typeResponse.setPlay(type.getPlay());
        typeResponse.setCorrectAnswer(type.isCorrectAnswer());
        typeResponse.setRecord(type.getRecord());
        typeResponse.setUploadImage(type.getUploadImage());
        typeResponse.setQuestionStatement(type.getQuestionStatement());
        typeResponse.setWordCounter(type.getWordCounter());
        typeResponse.setQuestionToThePassage(type.getQuestionToThePassage());
        typeResponse.setPassage(type.getPassage());
        typeResponse.setHighlightCorrectAnswer(type.getHighlightCorrectAnswer());
        return typeResponse;
    }
}
