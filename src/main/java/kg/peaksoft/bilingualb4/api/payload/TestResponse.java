package kg.peaksoft.bilingualb4.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class TestResponse {

    private String id;
    private String title;
    @JsonProperty("short_description")
    private String shortDescription;
    private List<QuestionResponse>questionResponseList;
}
