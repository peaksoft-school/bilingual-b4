package kg.peaksoft.bilingualb4.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "options")
@Getter
@Setter
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "option_sequence")
    @SequenceGenerator(name = "option_sequence", sequenceName = "option_seq", allocationSize = 1)
    @JsonIgnore
    private Long id;
    private String optionName;
    private boolean isCorrect = false;
    private String audio;

}