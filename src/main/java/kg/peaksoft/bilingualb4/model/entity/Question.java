package kg.peaksoft.bilingualb4.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.peaksoft.bilingualb4.model.enums.QuestionType;
import kg.peaksoft.bilingualb4.model.enums.SingleAndMultiType;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "questions")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "type_sequence")
    @SequenceGenerator(name = "type_sequence", sequenceName = "type_seq", allocationSize = 1)
    private Long id;

    private String name;
    @Enumerated(EnumType.STRING)
    private SingleAndMultiType singleAndMultiType;

    @OneToMany(cascade = {MERGE, DETACH, REFRESH, REMOVE, PERSIST}, fetch = FetchType.EAGER)
    private List<Word> wordList;
    private int duration;
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

    @ManyToOne(cascade = {REFRESH, DETACH, MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    @JsonIgnore
    private Test test;

    public void getDuration(){
        getTimer(duration);
    }

    public static void getTimer(int minute) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int seconds = minute * 60;
            @Override
            public void run() {
                if (seconds != 1) {
                    System.out.println("it's working " + (--seconds));
                } else {
                    System.out.println("it's done");
                    timer.cancel();
                }
            }
        };
        timer.schedule(task, 0, 1000);
    }
}
