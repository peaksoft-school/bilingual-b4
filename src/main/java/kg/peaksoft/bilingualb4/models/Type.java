package kg.peaksoft.bilingualb4.models;

import kg.peaksoft.bilingualb4.models.Word;
import kg.peaksoft.bilingualb4.models.enums.QuestionType;
import kg.peaksoft.bilingualb4.models.enums.SingleAndMultiType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "types")
@Getter
@Setter
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "type_sequence")
    @SequenceGenerator(name = "type_sequence", sequenceName = "type_seq", allocationSize = 1)
    private Long id;

    private String name;
    private SingleAndMultiType singleAndMultiType;

    @OneToMany(cascade =CascadeType.ALL)
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
