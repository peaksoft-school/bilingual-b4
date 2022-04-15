package kg.peaksoft.bilingualb4.apies;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.bilingualb4.dto.request.AuthInfoRequest;
import kg.peaksoft.bilingualb4.dto.response.AuthInfoResponse;
import kg.peaksoft.bilingualb4.services.AuthInfoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

@RestController
@AllArgsConstructor
@RequestMapping("/api/login")
@Tag(name = "Authentication:",description = "User authentication")
public class AuthInfoApi {

    private final AuthInfoService authInfoService;

    @Operation(summary = "Retrieve Authentication Token",
            description = "This entrypoint returns a JWT auth_token for authenticating further requests to the API.")
    @PostMapping
    @PermitAll
    public AuthInfoResponse save(@RequestBody AuthInfoRequest authInfoRequest) {
        return authInfoService.returnToken(authInfoRequest);
    }
}
