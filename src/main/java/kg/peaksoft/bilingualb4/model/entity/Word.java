package kg.peaksoft.bilingualb4.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

//    @JsonIgnore
//    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
//    private Question question;

}