package kg.peaksoft.bilingualb4.services.impl;

import kg.peaksoft.bilingualb4.api.payload.MyResultResponse;
import kg.peaksoft.bilingualb4.exception.NotFoundException;
import kg.peaksoft.bilingualb4.model.entity.MyResult;
import kg.peaksoft.bilingualb4.model.entity.User;
import kg.peaksoft.bilingualb4.model.mappers.MyResultMapper;
import kg.peaksoft.bilingualb4.repository.MyResultRepository;
import kg.peaksoft.bilingualb4.repository.UserRepository;
import kg.peaksoft.bilingualb4.services.MyResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyResultServiceImpl implements MyResultService {

    private final MyResultRepository resultRepository;
    private final MyResultMapper myResultMapper;
    private final UserRepository userRepository;

    @Override
    public MyResultResponse findById(Long id) {
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
        return myResultMapper.mapToResponse(myResult);
    }

    @Override
    public List<MyResultResponse> findAll(UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername());
        List<MyResult> myResults = resultRepository.findAllByUserId(user.getId());
        return myResultMapper.mapToResponse(myResults);
    }
}
