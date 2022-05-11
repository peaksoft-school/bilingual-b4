package kg.peaksoft.bilingualb4.services.impl;

import kg.peaksoft.bilingualb4.api.payload.MyResultResponse;
import kg.peaksoft.bilingualb4.exception.NotFoundException;
import kg.peaksoft.bilingualb4.model.entity.MyResult;
import kg.peaksoft.bilingualb4.model.mappers.MyResultMapper;
import kg.peaksoft.bilingualb4.repository.MyResultRepository;
import kg.peaksoft.bilingualb4.services.MyResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MyResultServiceImpl implements MyResultService {

    private final MyResultRepository resultRepository;
    private final MyResultMapper myResultMapper;

    @Override
    public MyResultResponse findById(Long id) {
        log.info("Successful find by id {} :",id);
        return myResultMapper.mapToResponse(resultRepository.findById(id).orElseThrow(()-> new NotFoundException(
                String.format("Object 'myResult with %d id not found!", id)
        )));
    }

    @Override
    public MyResultResponse deleteById(Long id) {
        MyResult myResult = resultRepository.findById(id).orElseThrow(() ->
                new NotFoundException(
                        String.format("Result with %d id not found!", id)));
        resultRepository.deleteById(id);
        log.info("Successful delete by this id {} :",id);
        return myResultMapper.mapToResponse(myResult);
    }

    @Override
    public List<MyResultResponse> findAll(Long userId) {
        List<MyResult> myResults = resultRepository.findAllById(userId);
        log.info("Successful find all !");
        return myResultMapper.mapToResponse(myResults);
    }
}
