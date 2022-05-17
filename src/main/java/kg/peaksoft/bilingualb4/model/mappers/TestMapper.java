package kg.peaksoft.bilingualb4.model.mappers;

import kg.peaksoft.bilingualb4.api.payload.TestRequest;
import kg.peaksoft.bilingualb4.api.payload.TestResponse;
import kg.peaksoft.bilingualb4.model.entity.Test;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class TestMapper {

    private final QuestionMapper questionMapper;

    public Test mapToEntity(Long id, TestRequest testRequest) {
        if (testRequest == null) {
            return null;
        }
        return Test.builder()
                .id(id)
                .title(testRequest.getTitle())
                .shortDescription(testRequest.getShortDescription())
                .isActive(testRequest.isActive())
                .build();
    }

    public List<TestResponse> mapToResponse(List<Test> testList) {
        List<TestResponse> responses = new ArrayList<>();
        for (Test test : testList) {
            responses.add(mapToResponse(test));
        }
        return responses;
    }

    public TestResponse mapToResponse(Test test) {
        if (test.getQuestionList() == null) {
            return TestResponse.builder()
                    .id(String.valueOf(test.getId()))
                    .title(test.getTitle())
                    .shortDescription(test.getShortDescription())
                    .isActive(test.isActive())
                    .build();
        } else {
            return TestResponse.builder()
                    .id(String.valueOf(test.getId()))
                    .title(test.getTitle())
                    .shortDescription(test.getShortDescription())
                    .isActive(test.isActive())
                    .questionResponseList(questionMapper.mapToResponse(test.getQuestionList()))
                    .build();
        }
    }
}
