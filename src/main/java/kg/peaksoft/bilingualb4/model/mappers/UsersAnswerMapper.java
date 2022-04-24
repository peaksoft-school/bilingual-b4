package kg.peaksoft.bilingualb4.model.mappers;

import kg.peaksoft.bilingualb4.api.payload.UsersAnswerRequest;
import kg.peaksoft.bilingualb4.api.payload.UsersAnswerResponse;
import kg.peaksoft.bilingualb4.model.entity.UsersAnswer;
import kg.peaksoft.bilingualb4.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsersAnswerMapper {

    private final QuestionRepository questionRepository;

    public UsersAnswer mapToEntity(Long id, Long questionId, UsersAnswerRequest usersAnswerRequest) {
        return UsersAnswer.builder()
                .id(id)
                .singleAndMultiType(usersAnswerRequest.getSingleAndMultiType())
                .optionList(usersAnswerRequest.getOptionList())
                .playButtonOfAudio(usersAnswerRequest.getPlayButtonOfAudio())
                .someText(usersAnswerRequest.getSomeText())
                .numberOfReplays(usersAnswerRequest.getNumberOfReplays())
                .image(usersAnswerRequest.getImage())
                .record(usersAnswerRequest.getRecord())
                .textOfRecord(usersAnswerRequest.getTextOfRecord())
                .counterOfWord(usersAnswerRequest.getCounterOfWord())
                .question(questionRepository.findById(questionId).get())
                .build();
    }

    public UsersAnswerResponse mepToResponse(UsersAnswer usersAnswer) {
        return UsersAnswerResponse.builder()
                .id(String.valueOf(usersAnswer.getId()))
                .singleAndMultiType(usersAnswer.getSingleAndMultiType())
                .optionList(usersAnswer.getOptionList())
                .playButtonOfAudio(usersAnswer.getPlayButtonOfAudio())
                .someText(usersAnswer.getSomeText())
                .numberOfReplays(usersAnswer.getNumberOfReplays())
                .image(usersAnswer.getImage())
                .record(usersAnswer.getRecord())
                .textOfRecord(usersAnswer.getTextOfRecord())
                .counterOfWord(usersAnswer.getCounterOfWord())
                .build();

    }
}
