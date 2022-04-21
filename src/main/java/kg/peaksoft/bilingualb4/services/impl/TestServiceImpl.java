package kg.peaksoft.bilingualb4.services.impl;

import kg.peaksoft.bilingualb4.api.payload.TestRequest;
import kg.peaksoft.bilingualb4.api.payload.TestResponse;
import kg.peaksoft.bilingualb4.exception.BadRequestException;
import kg.peaksoft.bilingualb4.exception.NotFoundException;
import kg.peaksoft.bilingualb4.model.entity.Question;
import kg.peaksoft.bilingualb4.model.entity.Test;
import kg.peaksoft.bilingualb4.model.entity.User;
import kg.peaksoft.bilingualb4.model.mappers.editMapper.TestEditMapper;
import kg.peaksoft.bilingualb4.model.mappers.viewMapper.TestViewMapper;
import kg.peaksoft.bilingualb4.repository.TestRepository;
import kg.peaksoft.bilingualb4.services.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final TestEditMapper testEditMapper;
    private final TestViewMapper testViewMapper;

    @Override
    public List<Test> findAll() {
        log.info("Fetching all test");
        return testRepository.findAll();
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
        Test test = testEditMapper.create(testRequest);
        Test save = testRepository.save(test);
        return testViewMapper.view(save);
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
    public void deleteById(Long id) {
        boolean exists = testRepository.existsById(id);
        if (!exists) {
            throw new BadRequestException(
                    String.format("Type with id %s does not exists", id));
        }
        testRepository.deleteById(id);
    }

    @Override
    public TestResponse updateById(Long id, TestRequest testRequest) {
        Test test = getById(id);

        String currentName = test.getTitle();
        String newName = testRequest.getTitle();

        if (!currentName.equals(newName)) {
            test.setTitle(newName);
        }

        String currentEmail = test.getShortDescription();
        String newEmail = testRequest.getShortDescription();

        if (!currentEmail.equals(newEmail)) {
            test.setShortDescription(newEmail);
        }
        return testViewMapper.view(test);
    }

    private Test getById(Long id) {
        return testRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("User with id = %s does not exists", id)
        ));
    }
}

