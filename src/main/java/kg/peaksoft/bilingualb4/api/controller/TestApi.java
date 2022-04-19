package kg.peaksoft.bilingualb4.api.controller;

import kg.peaksoft.bilingualb4.api.payload.TestRequest;
import kg.peaksoft.bilingualb4.api.payload.TestResponse;
import kg.peaksoft.bilingualb4.model.entity.Test;
import kg.peaksoft.bilingualb4.services.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TestApi {

    private final TestService testService;

    @GetMapping
    public List<Test> findAll() {
        return testService.findAll();
    }

    @PostMapping("/registration")
    public TestResponse registration(@RequestBody TestRequest testRequest) {
        return testService.save(testRequest);
    }

    @DeleteMapping("{id}")
    public void deleteBy(@PathVariable("id") Long id) {
        testService.deleteById(id);
    }

    @GetMapping("getBy/{id}")
    public Optional<Test> findById(@PathVariable Long id) {
        return testService.findById(id);
    }

    @PutMapping("{id}")
    public TestResponse update(@PathVariable Long id, @RequestBody TestRequest testRequest) {
        return testService.updateById(id, testRequest);
    }
}