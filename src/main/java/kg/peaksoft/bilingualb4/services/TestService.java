package kg.peaksoft.bilingualb4.services;

import kg.peaksoft.bilingualb4.api.payload.TestRequest;
import kg.peaksoft.bilingualb4.api.payload.TestResponse;
import kg.peaksoft.bilingualb4.model.entity.Test;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface TestService {

    List<TestResponse> findAll(UserDetails userDetails);

    TestResponse save(TestRequest testRequest);

    TestResponse findById(UserDetails userDetails, Long id);

    TestResponse deleteById(Long id);

    TestResponse updateById(Long id, TestRequest testRequest);
}
