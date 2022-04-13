package kg.peaksoft.bilingualb4.apies;

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
public class AuthInfoApi {

    private final AuthInfoService authInfoService;

    @PostMapping
    @PermitAll
    public AuthInfoResponse save(@RequestBody AuthInfoRequest authInfoRequest) {
        return authInfoService.returnToken(authInfoRequest);
    }
}
