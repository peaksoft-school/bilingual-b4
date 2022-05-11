package kg.peaksoft.bilingualb4.services.impl;

import kg.peaksoft.bilingualb4.api.payload.TestResultResponse;
import kg.peaksoft.bilingualb4.exception.BadRequestException;
import kg.peaksoft.bilingualb4.exception.NotFoundException;
import kg.peaksoft.bilingualb4.model.entity.MyResult;
import kg.peaksoft.bilingualb4.model.mappers.TestResultMapper;
import kg.peaksoft.bilingualb4.repository.MyResultRepository;
import kg.peaksoft.bilingualb4.services.TestResultService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TestResultServiceImpl implements TestResultService {

    private final MyResultRepository myResultRepository;
    private final TestResultMapper testResultMapper;

    @Override
    public TestResultResponse findById(Long id) {
        log.info("Successful find by this id {} :",id);
        return testResultMapper.mapToResponse(myResultRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format(
                        "Object 'myResult' with %d id not found", id
                ))
        ));
    }

    @Override
    public List<TestResultResponse> findAll() {
        List<MyResult> myResults = myResultRepository.findAll();
        log.info("Successful find all objects!");
        return testResultMapper.mapToResponse(myResults);
    }

    @Override
    public TestResultResponse updateById(Long id, int score) {
        MyResult myResult = myResultRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Object 'myResult with %d id not found!'", id)

        ));
        if (myResult.getScore() > 0) {
            myResult.setScore(score);
            myResultRepository.save(myResult);
        }else {
            throw new BadRequestException("This question checked automatically");
        }
        log.info("Successful update object by id {} : ",id);//???
        return testResultMapper.mapToResponse(myResult);
    }

    @Override
    public TestResultResponse deleteById(Long id) {
        MyResult myResult = myResultRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Object 'myResult with %d id not found!'", id)

        ));
        myResultRepository.deleteById(id);
        log.info("Successful delete by id {} :",id);
        return testResultMapper.mapToResponse(myResult);
    }
}
