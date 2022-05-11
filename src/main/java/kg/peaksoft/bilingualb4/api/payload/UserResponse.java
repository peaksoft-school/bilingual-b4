package kg.peaksoft.bilingualb4.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.peaksoft.bilingualb4.model.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private String id;
    @JsonProperty("user_name")
    private String userName;
    private String email;


}
