package kg.peaksoft.bilingualb4.model.entity;

import jdk.jshell.Snippet;
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
@Table(name = "my_results")
public class MyResult {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_result_sequence")
    @SequenceGenerator(name = "my_result_sequence", sequenceName = "my_result_seq", allocationSize = 1)
    private Long id;

    private LocalDateTime dateOfSubmission;

    private String testName;

    @Enumerated(EnumType.STRING)
    private Status status;

    private int score;

}
