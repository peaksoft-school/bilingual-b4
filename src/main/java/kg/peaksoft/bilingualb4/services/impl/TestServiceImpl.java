package kg.peaksoft.bilingualb4.services.impl;

import kg.peaksoft.bilingualb4.api.payload.TestRequest;
import kg.peaksoft.bilingualb4.api.payload.TestResponse;
import kg.peaksoft.bilingualb4.exception.BadRequestException;
import kg.peaksoft.bilingualb4.exception.NotFoundException;
import kg.peaksoft.bilingualb4.model.entity.Test;
import kg.peaksoft.bilingualb4.model.mappers.TestMapper;
import kg.peaksoft.bilingualb4.repository.TestRepository;
import kg.peaksoft.bilingualb4.services.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final TestMapper testMapper;

    @Override
    public List<TestResponse> findAll() {
        log.info("Fetching all test");
        return testMapper.mapToResponse(testRepository.findAll());
    }

    @Override
    public TestResponse save(TestRequest testRequest) {
        String title = testRequest.getTitle();
        boolean exists = testRepository.existsByName(title);

        if (exists) {
            throw new BadRequestException(
                    String.format("type with title = %s has already exists", title)
            );
        }
        Test test = testMapper.mapToEntity(null, testRequest);
        Test save = testRepository.save(test);
        return testMapper.mapToResponse(save);
    }

    @Override
    public Optional<Test> findById(Long id) {
        boolean exists = testRepository.existsById(id);
        if (!exists) {
            throw new BadRequestException("You should write one of {id} to get Type");
        }
        return testRepository.findById(id);
    }

    @Override
    public TestResponse deleteById(Long id) {
        TestResponse testResponse = getById(id);
        boolean exists = testRepository.existsById(id);
        if (!exists) {
            throw new BadRequestException(
                    String.format("Type with id %s does not exists", id));
        }
        testRepository.deleteById(id);
        return testResponse;
    }

    @Override
    public TestResponse updateById(Long id, TestRequest testRequest) {
        TestResponse test = getById(id);
        boolean exists = testRepository.existsById(id);
        Test response;
        if (!exists) {
            throw new BadRequestException(
                    String.format("question with %d is already exists", id)
            );
        } else {
            response = testMapper.mapToEntity(id, testRequest);
            testRepository.save(response);
        }
        return testMapper.mapToResponse(response);
    }

    private TestResponse getById(Long id) {
        return testMapper.mapToResponse(testRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("User with id = %s does not exists", id)
        )));
    }
}

