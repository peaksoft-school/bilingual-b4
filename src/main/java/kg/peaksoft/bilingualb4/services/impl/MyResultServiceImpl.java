package kg.peaksoft.bilingualb4.services.impl;

import kg.peaksoft.bilingualb4.api.payload.MyResultResponse;
import kg.peaksoft.bilingualb4.exception.NotFoundException;
import kg.peaksoft.bilingualb4.model.entity.MyResult;
import kg.peaksoft.bilingualb4.model.mappers.MyResultMapper;
import kg.peaksoft.bilingualb4.repository.MyResultRepository;
import kg.peaksoft.bilingualb4.services.MyResultService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MyResultServiceImpl implements MyResultService {

    private final MyResultRepository resultRepository;
    private final MyResultMapper myResultMapper;

    @Override
    public MyResultResponse deleteUserResultById(Long id) {
        MyResult myResult = resultRepository.findById(id).orElseThrow(() ->
                new NotFoundException(
                        String.format("Result with %d id not found!", id)));
        resultRepository.deleteById(id);
        return myResultMapper.mapToResponse(myResult);
    }

    @Override
    public List<MyResultResponse> findAll(Long id) {
        List<MyResult> myResults = resultRepository.findAllById(id);
        return myResultMapper.mapToResponse(myResults);
    }
}
