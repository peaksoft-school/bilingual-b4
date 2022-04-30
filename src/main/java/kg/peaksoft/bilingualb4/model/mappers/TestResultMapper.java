package kg.peaksoft.bilingualb4.model.mappers;

import kg.peaksoft.bilingualb4.api.payload.TestResultRequest;
import kg.peaksoft.bilingualb4.api.payload.TestResultResponse;
import kg.peaksoft.bilingualb4.model.entity.TestResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TestResultMapper {

    public TestResultResponse mapToResponse(TestResult testResult){
        return TestResultResponse.builder()
                .id(testResult.getId())
                .userName(testResult.getUserName())
                .dateOfSubmission(testResult.getDateOfSubmission())
                .testName(testResult.getTestName())
                .status(testResult.getStatus())
                .score(testResult.getScore())
                .build();
    }

    public TestResult mapToEntity(Long id, TestResultRequest testResultRequest){
        return TestResult.builder()
                .id(id)
                .userName(testResultRequest.getUserName())
                .dateOfSubmission(testResultRequest.getDateOfSubmission())
                .testName(testResultRequest.getTestName())
                .status(testResultRequest.getStatus())
                .score(testResultRequest.getScore())
                .build();
    }

    public List<TestResultResponse> mapToResponse(List<TestResult>testResultList){
        List<TestResultResponse> testResultResponses = new ArrayList<>();
        for (TestResult testResult: testResultList){
            testResultResponses.add(mapToResponse(testResult));
        }
        return testResultResponses;
    }

}
