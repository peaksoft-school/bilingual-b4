package kg.peaksoft.bilingualb4.repository;

import kg.peaksoft.bilingualb4.api.payload.UserRequest;
import kg.peaksoft.bilingualb4.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void existsByEmail() {
        try {
            UserRequest userRequest = new UserRequest();
            userRequest.setUserName("Talgarbek");
            userRequest.setEmail("talgarbek@gmail.com");
            userRequest.setPassword("take01");
            userService.registration(userRequest);

            boolean exists = userRepository.existsByEmail("talgarbek@gmail.com");
            assertThat(exists).isTrue();

        }catch (Exception e) {
            Assertions.fail("Mail saving error.\n");
        }
    }
}