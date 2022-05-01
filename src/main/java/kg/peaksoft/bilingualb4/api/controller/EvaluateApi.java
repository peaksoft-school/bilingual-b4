package kg.peaksoft.bilingualb4.api.controller;

import kg.peaksoft.bilingualb4.api.payload.EvaluateResponse;
import kg.peaksoft.bilingualb4.services.EvaluateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/evaluate")
public class EvaluateApi {

    private final EvaluateService evaluateService;

    //@PreAuthorize("hasAnyAuthority('ADMIN')")

    @PostMapping("{id}")
    public EvaluateResponse save(@PathVariable Long id){
        return evaluateService.save(id);
    }
}
