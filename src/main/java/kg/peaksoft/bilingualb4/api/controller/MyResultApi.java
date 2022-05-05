package kg.peaksoft.bilingualb4.api.controller;

import kg.peaksoft.bilingualb4.api.payload.MyResultResponse;
import kg.peaksoft.bilingualb4.api.payload.QuestionResultResponse;
import kg.peaksoft.bilingualb4.services.MyResultService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/my_result")
public class MyResultApi {

    private final MyResultService myResultService;

    @PreAuthorize("hasAnyAuthority('CLIENT')")
    @GetMapping("{userId}")
    public List<MyResultResponse> findAllById(@PathVariable Long userId) {
        return myResultService.findAll(userId);
    }

    @PreAuthorize("hasAnyAuthority('CLIENT')")
    @GetMapping("{id}")
    public MyResultResponse findById(@PathVariable Long id) {
        return myResultService.findById(id);
    }

    @PreAuthorize("hasAnyAuthority('CLIENT')")
    @DeleteMapping("{id}")
    public MyResultResponse delete(@PathVariable Long id) {
        return myResultService.deleteById(id);
    }
}
