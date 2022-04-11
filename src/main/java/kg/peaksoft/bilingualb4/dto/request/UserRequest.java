package kg.peaksoft.bilingualb4.dto.request;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    @NotNull
    private String userName;

    private String email;

    private String password;
}
