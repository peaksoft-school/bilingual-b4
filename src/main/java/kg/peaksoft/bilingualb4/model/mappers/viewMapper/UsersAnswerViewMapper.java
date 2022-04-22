package kg.peaksoft.bilingualb4.model.mappers.viewMapper;

import kg.peaksoft.bilingualb4.api.payload.UsersAnswerResponse;
import kg.peaksoft.bilingualb4.model.entity.UsersAnswer;
import org.springframework.stereotype.Component;

@Component
public class UsersAnswerViewMapper {

    public UsersAnswerResponse view(UsersAnswer usersAnswer) {
        if (usersAnswer == null) {
            return null;
        }
        UsersAnswerResponse usersAnswerResponse  = new UsersAnswerResponse();
        if (usersAnswer.getId() != null) {
            usersAnswerResponse.setId(String.valueOf(usersAnswer.getId()));
        }

        usersAnswerResponse.setSingleAndMultiType(usersAnswer.getSingleAndMultiType());
        usersAnswerResponse.setWordList(usersAnswer.getWordList());
        usersAnswerResponse.setPlayButtonOfAudio(usersAnswer.getPlayButtonOfAudio());
        usersAnswerResponse.setSomeText(usersAnswer.getSomeText());
        usersAnswerResponse.setNumberOfReplays(usersAnswer.getNumberOfReplays());
        usersAnswerResponse.setImage(usersAnswer.getImage());
        usersAnswerResponse.setRecord(usersAnswer.getRecord());
        usersAnswerResponse.setTextOfRecord(usersAnswer.getTextOfRecord());
        usersAnswerResponse.setCounterOfWord(usersAnswer.getCounterOfWord());
        return usersAnswerResponse;

    }
}
