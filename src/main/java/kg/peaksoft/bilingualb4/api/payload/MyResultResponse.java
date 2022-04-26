package kg.peaksoft.bilingualb4.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.peaksoft.bilingualb4.model.enums.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class MyResultResponse {

    private Long id;
    @JsonProperty("date_of_submission")
    private LocalDateTime dateOfSubmission;
    @JsonProperty("test_name")
    private String testName;
    private Status status;
    private int score;
}
