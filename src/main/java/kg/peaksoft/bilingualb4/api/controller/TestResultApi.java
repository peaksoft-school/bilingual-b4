package kg.peaksoft.bilingualb4.api.controller;

import kg.peaksoft.bilingualb4.api.payload.TestResultRequest;
import kg.peaksoft.bilingualb4.api.payload.TestResultResponse;
import kg.peaksoft.bilingualb4.services.TestResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test_result")
public class TestResultApi {

    private final TestResultService testResultService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping
    public List<TestResultResponse> findAll(){
        return testResultService.findAll();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("{id}")
    public TestResultResponse findById(@PathVariable Long id){
        return testResultService.findById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("{id}")
    public TestResultResponse deleteById(@PathVariable Long id){
        return testResultService.deleteById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("{id}")
    public TestResultResponse updateById(@PathVariable Long id, @RequestBody TestResultRequest testResultRequest){
        return testResultService.updateById(id, testResultRequest);
    }
}
