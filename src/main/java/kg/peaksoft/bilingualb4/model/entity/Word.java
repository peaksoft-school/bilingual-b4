package kg.peaksoft.bilingualb4.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "words")
@Getter
@Setter
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "word_sequence")
    @SequenceGenerator(name = "word_sequence", sequenceName = "word_seq", allocationSize = 1)
    private Long id;

    private String wordName;

    private boolean correctAnswer = false;

    @ManyToOne(cascade = {REFRESH,MERGE,DETACH},fetch = FetchType.EAGER)
    @JsonIgnore
    public UsersAnswer usersAnswer;
}