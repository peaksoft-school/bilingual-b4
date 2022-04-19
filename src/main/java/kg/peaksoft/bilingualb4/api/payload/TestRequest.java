package kg.peaksoft.bilingualb4.api.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.peaksoft.bilingualb4.model.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestRequest {

    private String title;
    private String shortDescription;


}
