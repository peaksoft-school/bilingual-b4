package kg.peaksoft.bilingualb4.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "options")
@Getter
@Setter
public class Options {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "option_sequence")
    @SequenceGenerator(name = "option_sequence", sequenceName = "option_seq", allocationSize = 1)
    private Long id;
    private String optionName;
    private boolean correctAnswer = false;
}