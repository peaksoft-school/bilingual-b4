package kg.peaksoft.bilingualb4.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "tests")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_seq")
    @SequenceGenerator(name = "test_seq", sequenceName = "test_seq", allocationSize = 1)
    private Long id;

    private String title;
    private String shortDescription;

    @ManyToMany(cascade = {REFRESH, DETACH, PERSIST, MERGE}, fetch = FetchType.LAZY, mappedBy = "testList")
    private List<User> userList;

    @OneToMany(cascade = ALL, mappedBy = "test", fetch = FetchType.LAZY)
    private List<Question> questionList;

    public void setUser(User user){
        if (userList == null) {
            userList = new ArrayList<>();
        }
        userList.add(user);
        user.setTest(this);
    }
}
