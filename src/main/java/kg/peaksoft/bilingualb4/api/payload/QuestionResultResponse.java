package kg.peaksoft.bilingualb4.api.payload;

import kg.peaksoft.bilingualb4.model.entity.Options;
import kg.peaksoft.bilingualb4.model.enums.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class QuestionResultResponse {

    private Long id;
    private String userName;
    private String testName;
    private LocalDateTime dateOfSubmission;
    private String questionName;
    private int score;
    private Status status;
    private double finalScore;
    private Status finalStatus;
    private List<OptionResponse> optionsResult;
    private List<OptionResponse> options;
    private int duration;
}
