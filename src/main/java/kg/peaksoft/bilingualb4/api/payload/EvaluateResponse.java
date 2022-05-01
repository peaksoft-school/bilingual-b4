package kg.peaksoft.bilingualb4.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.peaksoft.bilingualb4.model.entity.Options;
import kg.peaksoft.bilingualb4.model.entity.UsersAnswer;
import kg.peaksoft.bilingualb4.model.enums.QuestionType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class EvaluateResponse {

    //@JsonProperty("user_name")
    private String userName;
//    @JsonProperty("test_name")
    private String testName;
//    @JsonProperty("question_name")
    private String  questionName;
    private LocalDateTime duration;
//    @JsonProperty("question_type")
    private QuestionType questionType;
    private List<Options> options;
    private int score;
    //@JsonProperty("user_answer")
    private List<Options> userAnswer;
    //@JsonProperty("minimum_number_of_words")
    private int minimumNumberOfWords;
    //@JsonProperty("correct_answer")
    private String correctAnswer;
    //@JsonProperty("play_audio")
    private String playAudio;
    //@JsonProperty("entered_statement")
    private String enteredStatement;
    //@JsonProperty("number_of_plays")
    private int numberOfPlays;
    private String statement;
    //@JsonProperty("question_statement")
    private String questionStatement;
    //@JsonProperty("number_of_words")
    private int numberOfWords;
    private String passage;
}
