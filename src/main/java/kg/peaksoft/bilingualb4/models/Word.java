package kg.peaksoft.bilingualb4.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    private boolean correctAnswer;

    @Transient
    private Long typeId;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private Type type;

}