package kg.peaksoft.bilingualb4.api.payload;

import kg.peaksoft.bilingualb4.model.entity.Options;
import kg.peaksoft.bilingualb4.model.enums.QuestionType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class EvaluateResponse {

    private String userName;
    private String testName;
    private String  questionName;
    private int duration;
    private QuestionType questionType;
    private List<Options> options;
    private int score;
    private List<UsersAnswerResponse> userAnswer;
    private int minimumNumberOfWords;
    private String correctAnswer;
    private String playAudio;
    private String enteredStatement;
    private int numberOfPlays;
    private String statement;
    private String questionStatement;
    private int numberOfWords;
    private String passage;
}
