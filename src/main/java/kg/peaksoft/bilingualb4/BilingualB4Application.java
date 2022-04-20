package kg.peaksoft.bilingualb4;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@SpringBootApplication
@RestController
public class BilingualB4Application {
    @GetMapping
    public String checkHealth(){
        return "ok";
    }
    public static void main(String[] args) {
        SpringApplication.run(BilingualB4Application.class, args);
        System.out.println("Welcome colleagues, project name is Bilingual!");
    }

}
