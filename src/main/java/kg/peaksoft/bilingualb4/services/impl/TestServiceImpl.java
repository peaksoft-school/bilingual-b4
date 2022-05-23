package kg.peaksoft.bilingualb4.services.impl;

import kg.peaksoft.bilingualb4.api.payload.TestRequest;
import kg.peaksoft.bilingualb4.api.payload.TestResponse;
import kg.peaksoft.bilingualb4.exception.BadRequestException;
import kg.peaksoft.bilingualb4.exception.NotFoundException;
import kg.peaksoft.bilingualb4.model.entity.Test;
import kg.peaksoft.bilingualb4.model.entity.User;
import kg.peaksoft.bilingualb4.model.mappers.TestMapper;
import kg.peaksoft.bilingualb4.repository.TestRepository;
import kg.peaksoft.bilingualb4.repository.UserRepository;
import kg.peaksoft.bilingualb4.services.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final UserRepository userRepository;
    private final TestMapper testMapper;

    @Override
    public List<?> findAll(Principal principal) {
        log.info("Fetching all test");
        List<Test> testList = testRepository.findAllByActive();
        User user = userRepository.findByEmail(principal.getName());
        for (int i = 0; i < 2; i++) {
            if (user.getAuthInfo().getRoles().get(i).getName().equals("CLIENT")) {
                return testMapper.mapToResponseForClient(testList);
            }
            if (user.getAuthInfo().getRoles().get(i).getName().equals("ADMIN")) {
                List<Test> testList1 = testRepository.findAll();
                List<Test> activeList = new ArrayList<>();
                List<Test> inActiveList = new ArrayList<>();
                List<Test> allTestAfterSorted = new ArrayList<>();
                for (Test s : testList1) {
                    if (s.isActive()) {
                        activeList.add(s);
                    } else {
                        inActiveList.add(s);
                    }
                }

                List<Test> sortedByDate = activeList.stream()
                        .sorted(Comparator.comparing(Test::getCreatedOn))
                        .collect(Collectors.toList());

                allTestAfterSorted.addAll(sortedByDate);
                allTestAfterSorted.addAll(inActiveList);
                return testMapper.mapToResponse(allTestAfterSorted);
            }
        }

        return null;
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
    public Object findById(UserDetails userDetails, Long id) {
        boolean exists = testRepository.existsById(id);
        User user = userRepository.findByEmail(userDetails.getUsername());
        for (int i = 0; i < 2; i++) {
            if (user.getAuthInfo().getRoles().get(i).getName().equals("CLIENT")) {
                if (!exists) {
                    throw new BadRequestException("You should write one of {id} to get Test");
                }
                Test test = testRepository.findById(id).orElseThrow(() -> new NotFoundException(
                        String.format("Object 'test' with %d id not found!", id)
                ));
                if (test.isActive()) {
                    return testMapper.mapToResponseForClient(test);
                } else {
                    throw new BadRequestException("This test is not active");
                }
            }
            if (user.getAuthInfo().getRoles().get(i).getName().equals("ADMIN")) {
                if (!exists) {
                    throw new BadRequestException("You should write one of {id} to get Test");
                }
                Test test = testRepository.findById(id).orElseThrow(() -> new NotFoundException(
                        String.format("Object 'test' with %d id not found!", id)
                ));
                return testMapper.mapToResponse(test);
            }
        }
        return null;
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

