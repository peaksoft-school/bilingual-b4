package kg.peaksoft.bilingualb4.api.controller;

import kg.peaksoft.bilingualb4.api.payload.EvaluateResponse;
import kg.peaksoft.bilingualb4.api.payload.QuestionResultRequest;
import kg.peaksoft.bilingualb4.api.payload.QuestionResultResponse;
import kg.peaksoft.bilingualb4.model.entity.QuestionResult;
import kg.peaksoft.bilingualb4.services.QuestionResultService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/question_result")
public class QuestionResultApi {

    private final QuestionResultService questionResultService;

    //@PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping
    public List<QuestionResultResponse> findAll(){
        return questionResultService.findAll();
    }

    //@PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("{id}")
    public QuestionResultResponse findById(@PathVariable Long id){
        return questionResultService.findById(id);
    }

    //@PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("{id}")
    public EvaluateResponse updateById(@PathVariable Long id, @RequestBody(required = false) QuestionResultRequest questionResultRequest){
        return questionResultService.updateById(id, questionResultRequest);
    }
}
