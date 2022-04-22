package kg.peaksoft.bilingualb4.api.payload;

import kg.peaksoft.bilingualb4.model.entity.Question;
import kg.peaksoft.bilingualb4.model.entity.Word;
import kg.peaksoft.bilingualb4.model.enums.SingleAndMultiType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.REFRESH;

@Getter
@Setter
public class UsersAnswerRequest {

    @Enumerated(EnumType.STRING)
    private SingleAndMultiType singleAndMultiType;
    private List<Word> wordList;
    private String playButtonOfAudio;
    private String someText;
    private int numberOfReplays;
    private String image;
    private String record;
    private String textOfRecord;
    private int counterOfWord;




}
