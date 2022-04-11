package kg.peaksoft.bilingualb4.services.impl;

import kg.peaksoft.bilingualb4.dto.request.AuthInfoRequest;
import kg.peaksoft.bilingualb4.dto.response.AuthInfoResponse;
import kg.peaksoft.bilingualb4.jwt.JwtUtils;
import kg.peaksoft.bilingualb4.services.AuthInfoService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
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

        return AuthInfoResponse.builder()
                .email(authInfoRequest.getEmail())
                .token(generatedToken)
                .build();
    }
}
