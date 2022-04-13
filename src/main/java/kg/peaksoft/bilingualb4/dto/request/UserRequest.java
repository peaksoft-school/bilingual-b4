package kg.peaksoft.bilingualb4.dto.request;

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
    private String userName;

    @Email
    private String email;

    @ValidPassword
    private String password;
}
