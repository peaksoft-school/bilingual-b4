package kg.peaksoft.bilingualb4.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.peaksoft.bilingualb4.model.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "test_results")
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_result_sequence")
    @SequenceGenerator(name = "test_result_sequence", sequenceName = "test_result_seq", allocationSize = 1)
    private Long id;

    private String userName;
    private LocalDateTime dateOfSubmission;
    private String testName;
    @Enumerated(EnumType.STRING)
    private Status status;
    private int score;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<QuestionResult> questionResults;
}
