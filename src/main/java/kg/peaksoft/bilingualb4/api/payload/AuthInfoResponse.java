package kg.peaksoft.bilingualb4.api.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import kg.peaksoft.bilingualb4.model.entity.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AuthInfoResponse {

    private String email;

    private String token;

    @JsonProperty("user_name")
    private String userName;


    private String role;

}
