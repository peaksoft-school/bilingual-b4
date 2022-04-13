package kg.peaksoft.bilingualb4.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "authInfo")
@Getter
@Setter
@NoArgsConstructor
public class AuthInfo implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authInfo_sequence")
    @SequenceGenerator(name = "authInfo_sequence", sequenceName = "authInfo_seq", allocationSize = 1)
    Long id;
    private String email;
    private String password;
    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    @ManyToMany(cascade = {PERSIST,REMOVE},fetch = FetchType.EAGER)
    @JoinTable(name = "authInfo_roles",
            joinColumns = @JoinColumn(name = "authInfo_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    @JsonIgnore
    private List<Role> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.synchronizedCollection(roles);
    }

    @Override
    public String getUsername() {
        return email;
    }


    public void setAuthInfo1(Role role) {
        if (roles == null) {
            roles = new ArrayList<>();
        }
        roles.add(role);
        role.setAuthInfo1(this);
    }

}
