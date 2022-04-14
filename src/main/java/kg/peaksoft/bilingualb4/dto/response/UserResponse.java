package kg.peaksoft.bilingualb4.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private String id;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("email")
    private String email;
}
