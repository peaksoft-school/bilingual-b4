package kg.peaksoft.bilingualb4.dto.response;

import kg.peaksoft.bilingualb4.models.Word;
import kg.peaksoft.bilingualb4.models.enums.QuestionType;
import kg.peaksoft.bilingualb4.models.enums.SingleAndMultiType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TypeResponse {

    private String id;
    private String name;
    private SingleAndMultiType singleAndMultiType;
    private List<Word> wordList;
    private String audio;
    private int numberOfReplays;
    private String upload;
    private String play;
    private boolean correctAnswer;
    private String record;
    private String uploadImage;
    private String questionStatement;
    private int wordCounter;
    private String questionToThePassage;
    private String passage;
    private String highlightCorrectAnswer;
    private QuestionType questionType;
}
