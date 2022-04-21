package kg.peaksoft.bilingualb4.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "test")
@Getter
@Setter
@NoArgsConstructor
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_seq")
    @SequenceGenerator(name = "test_seq", sequenceName = "test_seq", allocationSize = 1)
    private Long id;

    private String title;
    private String shortDescription;

    @ManyToMany(cascade = {REFRESH, DETACH, PERSIST, MERGE}, mappedBy = "testList")
    private List<User> userList;

    @OneToMany(cascade = {MERGE, DETACH, REFRESH, REMOVE, PERSIST}, mappedBy = "test", fetch = FetchType.EAGER)
    private List<Question> questionList;

}
