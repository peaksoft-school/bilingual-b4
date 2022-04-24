package kg.peaksoft.bilingualb4.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "user_name")
    private String userName;
    private String email;
    private String password;

    @OneToOne(cascade = ALL)
    @JsonIgnore
    private AuthInfo authInfo;

    @ManyToMany(cascade = {MERGE, REFRESH, PERSIST, DETACH}, fetch = FetchType.LAZY)
    @JoinTable(name = "users_test",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "test_id"))
    @JsonIgnore
    private List<Test> testList;

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}
