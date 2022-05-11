package kg.peaksoft.bilingualb4.services;

import kg.peaksoft.bilingualb4.api.payload.TestRequest;
import kg.peaksoft.bilingualb4.api.payload.TestResponse;
import kg.peaksoft.bilingualb4.model.entity.Test;

import java.util.List;
import java.util.Optional;

public interface TestService {

    List<TestResponse> findAll();

    TestResponse save(TestRequest testRequest);

    TestResponse findById(Long id);

    TestResponse deleteById(Long id);

    TestResponse updateById(Long id, TestRequest testRequest);
}
