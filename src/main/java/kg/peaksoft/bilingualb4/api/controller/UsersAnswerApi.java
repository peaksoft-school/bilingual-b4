package kg.peaksoft.bilingualb4.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.bilingualb4.api.payload.UsersAnswerRequest;
import kg.peaksoft.bilingualb4.api.payload.UsersAnswerResponse;
import kg.peaksoft.bilingualb4.services.UsersAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "UserAnswer:", description = "Makes it possible to keep the user result")
@RequestMapping("/bilingual/api/answer")
public class UsersAnswerApi {

    private final UsersAnswerService usersAnswerService;

    @Operation(summary = "Create the new entity: workshop UsersAnswer, Role 'CLIENT'",
            description =
                    "The method expects a JSON with entity object in the request body." +
                            "The entity object may contain references to other entities.")
    @PreAuthorize("hasAnyAuthority('CLIENT')")
    @PostMapping("{questionId}")
    public UsersAnswerResponse save(@PathVariable Long questionId, @RequestBody UsersAnswerRequest usersAnswerRequest) {
        return usersAnswerService.save(questionId, usersAnswerRequest);
    }

    @Operation(summary = "Detach results of user, if client will press the button 'cancel'")
    @PreAuthorize("hasAnyAuthority('CLIENT')")
    @DeleteMapping("/cancel/{testId}")
    public void toCancel(@PathVariable Long testId) {
        usersAnswerService.toCancel(testId);
    }
}
