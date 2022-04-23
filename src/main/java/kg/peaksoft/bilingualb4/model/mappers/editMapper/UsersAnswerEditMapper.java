package kg.peaksoft.bilingualb4.model.mappers.editMapper;

import kg.peaksoft.bilingualb4.api.payload.UsersAnswerRequest;
import kg.peaksoft.bilingualb4.model.entity.UsersAnswer;
import kg.peaksoft.bilingualb4.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsersAnswerEditMapper {

    private final QuestionRepository questionRepository;

    public UsersAnswer create(Long id, UsersAnswerRequest usersAnswerRequest) {
        if (usersAnswerRequest == null) {
            return null;
        }
        UsersAnswer usersAnswer = new UsersAnswer();
        usersAnswer.setSingleAndMultiType(usersAnswerRequest.getSingleAndMultiType());
        usersAnswer.setOptionList(usersAnswerRequest.getOptionList());
        usersAnswer.setPlayButtonOfAudio(usersAnswerRequest.getPlayButtonOfAudio());
        usersAnswer.setSomeText(usersAnswerRequest.getSomeText());
        usersAnswer.setNumberOfReplays(usersAnswerRequest.getNumberOfReplays());
        usersAnswer.setImage(usersAnswerRequest.getImage());
        usersAnswer.setRecord(usersAnswerRequest.getRecord());
        usersAnswer.setTextOfRecord(usersAnswerRequest.getTextOfRecord());
        usersAnswer.setCounterOfWord(usersAnswerRequest.getCounterOfWord());
        usersAnswer.setQuestion(questionRepository.findById(id).get());
        return usersAnswer;
    }
}
