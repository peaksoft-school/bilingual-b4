package kg.peaksoft.bilingualb4.api.controller;

import kg.peaksoft.bilingualb4.api.payload.UsersAnswerRequest;
import kg.peaksoft.bilingualb4.api.payload.UsersAnswerResponse;
import kg.peaksoft.bilingualb4.services.UsersAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/answer/{id}")
public class UsersAnswerApi {

    private final UsersAnswerService usersAnswerService;

    @PostMapping
    public UsersAnswerResponse save(@PathVariable("id") Long id,@RequestBody UsersAnswerRequest usersAnswerRequest){
        return usersAnswerService.save(id,usersAnswerRequest);
    }
}
