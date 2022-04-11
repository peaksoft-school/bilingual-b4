package kg.peaksoft.bilingualb4.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthInfoResponse {

    private String email;

    private String token;

}
