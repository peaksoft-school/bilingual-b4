package kg.peaksoft.bilingualb4.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.peaksoft.bilingualb4.model.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class QuestionResultRequest {

    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("test_name")
    private String testName;
    @JsonProperty("date_of_submission")
    private LocalDateTime dateOfSubmission;
    @JsonProperty("question_name")
    private String questionName;
    private int score;
    private Status status;
    @JsonProperty("final_score")
    private int finalScore;
    @JsonProperty("final_status")
    private Status finalStatus;
}
