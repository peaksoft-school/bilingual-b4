package kg.peaksoft.bilingualb4.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.peaksoft.bilingualb4.model.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
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
    private int finalScore;
    @Enumerated(EnumType.STRING)
    private Status finalStatus;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "my_result_id")
    private MyResult myResult;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @Override
    public String toString() {
        return "QuestionResult{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", testName='" + testName + '\'' +
                ", dateOfSubmission=" + dateOfSubmission +
                ", questionName='" + questionName + '\'' +
                ", score=" + score +
                ", status=" + status +
                ", finalScore=" + finalScore +
                ", finalStatus=" + finalStatus +
                ", myResult=" + myResult +
                ", question=" + question +
                '}';
    }
}


