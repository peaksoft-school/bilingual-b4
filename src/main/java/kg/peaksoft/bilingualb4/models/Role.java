package kg.peaksoft.bilingualb4.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.REFRESH;

@Entity
@Getter @Setter
@Table(name = "roles")
@NoArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_sequence")
    @SequenceGenerator(name = "role_sequence", sequenceName = "role_seq", allocationSize = 1)
    private Long id;
    private String name;

    public Role(String name) {
        this.name = name;
    }

    @ManyToMany(cascade = {MERGE,DETACH,PERSIST,REFRESH},mappedBy = "roles",fetch = FetchType.EAGER)
    private List<AuthInfo> authInfo;


    public void setAuthInfo1(AuthInfo authInfos) {
        if (authInfo == null) {
            authInfo = new ArrayList<>();
        }
        authInfo.add(authInfos);
    }

    @Override
    public String getAuthority() {
        return this.name;
    }
}
