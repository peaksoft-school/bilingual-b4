package kg.peaksoft.bilingualb4.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.peaksoft.bilingualb4.model.enums.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class TestResultResponse {

    private Long id;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("date_of_submission")
    private LocalDateTime dateOfSubmission;
    @JsonProperty("test_name")
    private String testName;
    private Status status;
    private int score;
}
