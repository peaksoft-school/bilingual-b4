package kg.peaksoft.bilingualb4;

import kg.peaksoft.bilingualb4.model.entity.AuthInfo;
import kg.peaksoft.bilingualb4.model.entity.Role;
import kg.peaksoft.bilingualb4.model.entity.User;
import kg.peaksoft.bilingualb4.repository.RoleRepository;
import kg.peaksoft.bilingualb4.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@SpringBootApplication
@AllArgsConstructor
public class BilingualB4Application {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String greetingPage() {
        return "<h1>Welcome to Bilingual application!!!<h1/>";
    }

    public static void main(String[] args) {
        SpringApplication.run(BilingualB4Application.class, args);
        System.out.println("Welcome colleagues, project name is Bilingual!");
    }

    @PostConstruct
    public void init() {
        User user = new User();
        user.setUserName("Admin");
        user.setEmail("admin@gmail.com");
        user.setPassword(passwordEncoder.encode("admin"));

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
