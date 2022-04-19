package kg.peaksoft.bilingualb4.model.entity;

import kg.peaksoft.bilingualb4.model.enums.QuestionType;
import kg.peaksoft.bilingualb4.model.enums.SingleAndMultiType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "type_sequence")
    @SequenceGenerator(name = "type_sequence", sequenceName = "type_seq", allocationSize = 1)
    private Long id;

    private String name;
    @Enumerated(EnumType.STRING)
    private SingleAndMultiType singleAndMultiType;

    @OneToMany(cascade = {MERGE,DETACH,REFRESH,REMOVE,PERSIST}, fetch = FetchType.EAGER)
    private List<Word> wordList;
    private String audio;
    private int numberOfReplays;
    private String upload;
    private String play;
    private boolean correctAnswer = false;
    private String record;
    private String uploadImage;
    private String questionStatement;
    private int wordCounter;
    private String questionToThePassage;
    private String passage;
    private String highlightCorrectAnswer;
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    public void setWord(Word word){
        if (this.wordList==null){
            this.wordList = new ArrayList<>();
        }
        wordList.add(word);
    }
}
