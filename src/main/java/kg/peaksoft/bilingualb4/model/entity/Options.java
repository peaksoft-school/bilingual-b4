package kg.peaksoft.bilingualb4.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.DETACH;

@Entity
@Table(name = "options")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Options {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "option_sequence")
    @SequenceGenerator(name = "option_sequence", sequenceName = "option_seq", allocationSize = 1)
    @JsonIgnore
    private Long id;
    private String optionName;
    private String file;
    private boolean isCorrect = false;
    @ManyToMany(cascade = {MERGE, REFRESH, PERSIST, DETACH}, fetch = FetchType.LAZY)
    @JoinTable(name = "question_result_options",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id"))
    @JsonIgnore
    private List<QuestionResult> questionResults;

    public void addQuestionResult(QuestionResult result) {
        if (this.questionResults == null) {
            this.questionResults = new ArrayList<>();
        }
        this.questionResults.add(result);
    }
}