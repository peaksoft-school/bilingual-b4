package kg.peaksoft.bilingualb4.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthInfoRequest {

    private String email;

    private String password;
}
