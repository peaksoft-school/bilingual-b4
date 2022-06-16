package kg.peaksoft.bilingualb4.model.mappers;

import kg.peaksoft.bilingualb4.api.payload.OptionRequest;
import kg.peaksoft.bilingualb4.api.payload.QuestionResultResponse;
import kg.peaksoft.bilingualb4.model.entity.QuestionResult;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestionResultMapper {
    private final OptionMapper optionMapper;

    public QuestionResultResponse mapToResponse(QuestionResult questionResult) {
        return QuestionResultResponse.builder()
                .id(questionResult.getId())
                .userName(questionResult.getUserName())
                .testName(questionResult.getTestName())
                .dateOfSubmission(questionResult.getDateOfSubmission())
                .questionName(questionResult.getQuestionName())
                .score(questionResult.getScore())
                .status(questionResult.getStatus())
                .finalScore(questionResult.getFinalScore())
                .finalStatus(questionResult.getFinalStatus())
                .optionsResult(optionMapper.mapToResponse(questionResult.getOptions()))
                .options(optionMapper.mapToResponse(questionResult.getQuestion().getOptionsList()))
                .duration(questionResult.getQuestion().getDuration())
                .build();
    }

    public List<QuestionResultResponse> mapToResponse(List<QuestionResult> questionResultList) {
        List<QuestionResultResponse> responseList = new ArrayList<>();
        for (QuestionResult questionResult : questionResultList) {
            responseList.add(mapToResponse(questionResult));
        }
        return responseList;
    }
}
