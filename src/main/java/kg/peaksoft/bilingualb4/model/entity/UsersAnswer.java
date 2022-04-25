package kg.peaksoft.bilingualb4.model.entity;


import kg.peaksoft.bilingualb4.model.enums.SingleAndMultiType;
import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users_answers")
public class UsersAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_answer_sequence")
    @SequenceGenerator(name = "users_answer_sequence", sequenceName = "users_answer_seq", allocationSize = 1)
    private Long id;
    @Enumerated(EnumType.STRING)
    private SingleAndMultiType singleAndMultiType;

    @OneToMany(cascade = {MERGE, DETACH, REFRESH, REMOVE, PERSIST},fetch = FetchType.EAGER)
    private List<Options> optionsList;
    private String playButtonOfAudio;
    private String someText;
    private int numberOfReplays;
    private String image;
    private String record;
    private String textOfRecord;
    private int counterOfWord;

    @ManyToOne(cascade = {DETACH,MERGE,REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

}
