package kg.peaksoft.bilingualb4.service;

import kg.peaksoft.bilingualb4.api.payload.UserRequest;
import kg.peaksoft.bilingualb4.api.payload.UserResponse;
import kg.peaksoft.bilingualb4.model.entity.User;
import kg.peaksoft.bilingualb4.services.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    UserRequest userRequest = new UserRequest();

    public void newUser() {
        userRequest.setUserName("Akylbek");
        userRequest.setEmail("akylbeknurbekov01@gmail.com");
        userRequest.setPassword("akylbek01");
        userService.registration(userRequest);
    }

    @Test
    @Order(1)
    @Rollback(value = false)
    void registration() {
        try {
            newUser();
        } catch (Exception e) {
            Assertions.fail("An error occurred while registering clients: " + e.getMessage());
        }
    }

    @Test
    @Order(2)
    void findById() {
        try {
            User user = userService.findById(2L).get();
            assertThat(user).isNotNull();
            assertThat(user.getUserName()).isEqualTo("User");
            assertThat(user.getEmail()).isEqualTo("user@gmail.com");
        } catch (Exception e) {
            Assertions.fail("An error occurred while getting a client by id: " + e.getMessage());
        }
    }

    @Test
    @Order(3)
    void update() {
        try {
            UserRequest userRequest = new UserRequest();
            userRequest.setUserName("Talgarbek");
            userRequest.setEmail("talgarbek00@gmail.com");
            userRequest.setPassword("talgarbek00");
            userService.update(2L, userRequest);

        } catch (Exception e) {
            Assertions.fail("An error occurred while updating the client by id: " + e.getMessage());
        }
    }

    @Test
    @Order(4)
    void findAll() {
        try {
            List<UserResponse> userList = userService.findAll();
            if (userList.size() < 1) {
                Assertions.fail("There are no clients in the database");
            }
        } catch (Exception e) {
            Assertions.fail("An error occurred while getting clients: " + e.getMessage());
        }
    }

    @Test
    @Order(5)
    void deleteById() {
        try {
            userService.deleteById(2L);
        } catch (Exception e) {
            Assertions.fail("An error occurred while deleting clients by id: " + e.getMessage());
        }
    }
}
