package kg.peaksoft.bilingualb4.services.impl;

import kg.peaksoft.bilingualb4.api.payload.AuthInfoRequest;
import kg.peaksoft.bilingualb4.api.payload.AuthInfoResponse;
import kg.peaksoft.bilingualb4.config.jwt.JwtUtils;
import kg.peaksoft.bilingualb4.services.AuthInfoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AuthInfoServiceImpl implements AuthInfoService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public AuthInfoResponse returnToken(AuthInfoRequest authInfoRequest) {
        Authentication authentication;
        authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authInfoRequest.getEmail(),
                authInfoRequest.getPassword()));

        String generatedToken = jwtUtils.generateToken(authentication);
        log.info("Successful generated token {} :",generatedToken);
        return AuthInfoResponse.builder()
                .email(authInfoRequest.getEmail())
                .token(generatedToken)
                .build();
    }
}
