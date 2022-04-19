package kg.peaksoft.bilingualb4.services;

import kg.peaksoft.bilingualb4.api.payload.TestRequest;
import kg.peaksoft.bilingualb4.api.payload.TestResponse;
import kg.peaksoft.bilingualb4.model.entity.Test;

import java.util.List;
import java.util.Optional;

public interface TestService {

    List<Test> findAll();

    TestResponse save(TestRequest testRequest);

    Optional<Test> findById(Long id);

    void deleteById(Long id);

    TestResponse updateById(Long id, TestRequest testRequest);
}
