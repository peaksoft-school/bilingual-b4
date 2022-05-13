package kg.peaksoft.bilingualb4.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
//import kg.peaksoft.bilingualb4.annotations.ValidPassword;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserRequest {

    @NotBlank
    @JsonProperty("user_name")
    private String userName;

    @Email
    private String email;

    //@ValidPassword
    private String password;
}
