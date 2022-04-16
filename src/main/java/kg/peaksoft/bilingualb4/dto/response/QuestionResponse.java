package kg.peaksoft.bilingualb4.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.peaksoft.bilingualb4.models.Word;
import kg.peaksoft.bilingualb4.models.enums.QuestionType;
import kg.peaksoft.bilingualb4.models.enums.SingleAndMultiType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionResponse {

    private String id;
    private String name;
    @JsonProperty("single_and_multi_type")
    private SingleAndMultiType singleAndMultiType;
    @JsonProperty("word_list")
    private List<Word> wordList;
    private String audio;
    @JsonProperty("number_of_replays")
    private int numberOfReplays;
    private String upload;
    private String play;
    @JsonProperty("correct_answer")
    private boolean correctAnswer;
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
}
