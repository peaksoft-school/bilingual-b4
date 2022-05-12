package kg.peaksoft.bilingualb4.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.bilingualb4.api.payload.UsersAnswerRequest;
import kg.peaksoft.bilingualb4.api.payload.UsersAnswerResponse;
import kg.peaksoft.bilingualb4.services.UsersAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/answer")
@CrossOrigin(origins = "http://localhost:5000", maxAge = 3600)
@Tag(name = "UserAnswer:", description = "Makes it possible to keep the user result")
public class UsersAnswerApi {

    private final UsersAnswerService usersAnswerService;

    @Operation(summary = "Create the new entity: workshop ClientsAnswer, Role 'CLIENT'",
            description =
                    "The method expects a JSON with entity object in the request body." +
                            "The entity object may contain references to other entities.")
    @PreAuthorize("hasAnyAuthority('CLIENT')")
    @PostMapping("{questionId}")
    public UsersAnswerResponse save(@PathVariable Long questionId,
                                 @RequestBody UsersAnswerRequest usersAnswerRequest,
                                 @AuthenticationPrincipal UserDetails user) {
        return usersAnswerService.save(questionId, usersAnswerRequest, user);
    }

    @Operation(summary = "Detach results of user, if client will press the button 'cancel'")
    @PreAuthorize("hasAnyAuthority('CLIENT')")
    @DeleteMapping("/cancel/{testId}")
    public void toCancel(@PathVariable Long testId) {
        usersAnswerService.toCancel(testId);
    }
}
