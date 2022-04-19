package kg.peaksoft.bilingualb4;

import kg.peaksoft.bilingualb4.model.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;


@AllArgsConstructor
@SpringBootApplication

public class BilingualB4Application {

    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(BilingualB4Application.class, args);
        System.out.println("Welcome colleagues, project name is Bilingual!");
    }

    @PostConstruct
    public void initMethod(){
        User user=new User();
        user.setPassword(passwordEncoder.encode("admin"));
    }

}
