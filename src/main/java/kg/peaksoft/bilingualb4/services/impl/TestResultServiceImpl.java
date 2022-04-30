package kg.peaksoft.bilingualb4.services.impl;

import kg.peaksoft.bilingualb4.api.payload.TestResultRequest;
import kg.peaksoft.bilingualb4.api.payload.TestResultResponse;
import kg.peaksoft.bilingualb4.exception.BadRequestException;
import kg.peaksoft.bilingualb4.exception.NotFoundException;
import kg.peaksoft.bilingualb4.model.entity.TestResult;
import kg.peaksoft.bilingualb4.model.enums.Status;
import kg.peaksoft.bilingualb4.model.mappers.TestResultMapper;
import kg.peaksoft.bilingualb4.repository.TestResultRepository;
import kg.peaksoft.bilingualb4.services.TestResultService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TestResultServiceImpl implements TestResultService {

    private final TestResultRepository testResultRepository;
    private final TestResultMapper testResultMapper;

    @Override
    public List<TestResultResponse> findAll() {
        return testResultMapper.mapToResponse(testResultRepository.findAll());
    }

    @Override
    public TestResultResponse findById(Long id) {
        return testResultMapper.mapToResponse(testResultRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Test result with %d id not found!", id))));
    }

    @Override
    public TestResultResponse deleteById(Long id) {
        TestResultResponse testResultResponse = testResultMapper.mapToResponse(testResultRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Test result with %d id not found!", id))));
        testResultRepository.deleteById(id);
        return testResultResponse;
    }

    @Override
    public TestResultResponse updateById(Long id, TestResultRequest testResultRequest) {

        if (testResultRequest.getStatus() == Status.NOT_EVALUATE) {
            boolean exists = testResultRepository.existsById(id);
            TestResult response;
            if (!exists) {
                throw new BadRequestException(
                        String.format("question with %d is already exists", id)
                );
            } else {
                response = testResultMapper.mapToEntity(id, testResultRequest);
                testResultRepository.save(response);
            }
            return testResultMapper.mapToResponse(response);
        } else {
            throw new BadRequestException("Test is already evaluated!");
        }
    }
}
