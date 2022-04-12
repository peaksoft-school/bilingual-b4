package kg.peaksoft.bilingualb4;

import kg.peaksoft.bilingualb4.models.AuthInfo;
import kg.peaksoft.bilingualb4.models.Role;
import kg.peaksoft.bilingualb4.models.User;
import kg.peaksoft.bilingualb4.repositories.RoleRepository;
import kg.peaksoft.bilingualb4.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@AllArgsConstructor
@SpringBootApplication
public class BilingualB4Application {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(BilingualB4Application.class, args);
        System.out.println("Welcome colleagues, project name is Bilingual!");
    }


    @PostConstruct
    public void save() {
        User user = new User();
        user.setUserName("Muhammed");
        user.setEmail("muhammed@gmail.com");
        user.setPassword(passwordEncoder.encode("2003"));

        Role role = new Role();
        Role role1 = new Role();
        role.setName("ADMIN");

        AuthInfo authInfo = new AuthInfo();
        authInfo.setEmail(user.getEmail());
        authInfo.setPassword(user.getPassword());

        authInfo.setAuthInfo1(role);


        user.setAuthInfo(authInfo);
        userRepository.save(user);
        role1.setName("CLIENT");
        roleRepository.save(role1);
        authInfo.setAuthInfo1(role1);

    }
}
