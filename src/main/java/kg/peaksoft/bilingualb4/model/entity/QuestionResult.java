package kg.peaksoft.bilingualb4.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.peaksoft.bilingualb4.model.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.MERGE;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "question_result")
public class QuestionResult {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_result_sequence")
    @SequenceGenerator(name = "question_result_sequence", sequenceName = "question_result_seq", allocationSize = 1)
    private Long id;
    private String userName;
    private String testName;
    private LocalDateTime dateOfSubmission;
    private String questionName;
    private int score;
    @Enumerated(EnumType.STRING)
    private Status status;
    private double finalScore;
    private Status finalStatus;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "test_result_id")
    private TestResult testResult;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private MyResult myResult;
    @ManyToMany(cascade = {REFRESH, DETACH, PERSIST, MERGE}, fetch = FetchType.LAZY, mappedBy = "questionResults")
    private List<Options> options;

    public void addOption(Options option) {
        if (this.options == null) {
            this.options = new ArrayList<>();
        }
        this.options.add(option);
        option.addQuestionResult(this);
    }
}