package kg.peaksoft.bilingualb4.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.peaksoft.bilingualb4.model.entity.Options;
import kg.peaksoft.bilingualb4.model.enums.QuestionType;
import kg.peaksoft.bilingualb4.model.enums.SingleAndMultiType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class QuestionRequest {
    private String name;
    @JsonProperty("single_and_multi_type")
    private SingleAndMultiType singleAndMultiType;
    @JsonProperty("options_list")
    private List<Options> optionsList;
    private String audio;
    @JsonProperty("number_of_replays")
    private int numberOfReplays;
    private String upload;
    private String play;
    @JsonProperty("correct_answer")
    private String correctAnswer;
    private String record;
    @JsonProperty("upload_image")
    private String uploadImage;
    @JsonProperty("question_statement")
    private String questionStatement;
    @JsonProperty("word_counter")
    private int wordCounter;
    @JsonProperty("question_to_the_passage")
    private String questionToThePassage;
    private String passage;
    @JsonProperty("highlight_correct_answer")
    private String highlightCorrectAnswer;
    @JsonProperty("question_type")
    private QuestionType questionType;
    private LocalDateTime duration;
}
