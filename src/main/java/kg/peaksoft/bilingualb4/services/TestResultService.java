package kg.peaksoft.bilingualb4.services;

import kg.peaksoft.bilingualb4.api.payload.TestResultRequest;
import kg.peaksoft.bilingualb4.api.payload.TestResultResponse;

import java.util.List;

public interface TestResultService {

    List<TestResultResponse> findAll();

    TestResultResponse findById(Long id);

    TestResultResponse deleteById(Long id);

    TestResultResponse updateById(Long id, TestResultRequest testResultRequest);

}
