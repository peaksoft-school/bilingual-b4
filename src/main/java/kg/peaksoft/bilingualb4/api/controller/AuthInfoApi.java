package kg.peaksoft.bilingualb4.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.bilingualb4.api.payload.AuthInfoRequest;
import kg.peaksoft.bilingualb4.api.payload.AuthInfoResponse;
import kg.peaksoft.bilingualb4.services.AuthInfoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

@RestController
@AllArgsConstructor
@RequestMapping("/bilingual/api")
@Tag(name = "Authentication:",description = "User authentication")
public class AuthInfoApi {

    private final AuthInfoService authInfoService;

    @Operation(summary = "Retrieve Authentication Token",
            description = "This entrypoint returns a JWT auth_token for authenticating further requests to the API.")
    @PostMapping("/login")
    @PermitAll
    public AuthInfoResponse save(@RequestBody AuthInfoRequest authInfoRequest) {
        return authInfoService.returnToken(authInfoRequest);
    }
}
