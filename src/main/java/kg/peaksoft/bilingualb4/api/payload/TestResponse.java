package kg.peaksoft.bilingualb4.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestResponse {

    private String id;
    private String title;
    @JsonProperty("short_description")
    private String shortDescription;
}
