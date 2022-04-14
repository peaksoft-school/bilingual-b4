package kg.peaksoft.bilingualb4.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import kg.peaksoft.bilingualb4.annotations.ValidPassword;
import kg.peaksoft.bilingualb4.models.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
public class UserRequest {

    @NotNull
    @JsonProperty("user_name")
    private String userName;

    @Email
    @JsonProperty("email")
    private String email;

    @ValidPassword
    @JsonProperty("password")
    private String password;
}
