package kg.peaksoft.bilingualb4.services.impl;

import kg.peaksoft.bilingualb4.api.payload.UserRequest;
import kg.peaksoft.bilingualb4.api.payload.UserResponse;
import kg.peaksoft.bilingualb4.model.entity.User;
import kg.peaksoft.bilingualb4.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceImplTest {


    private UserService userService;

    private UserResponse userResponse;

    UserRequest userRequest = new UserRequest();

    public void newUser() {
        userRequest.setUserName("Akylbek");
        userRequest.setEmail("akylbeknurbekov01@gmail.com");
        userRequest.setPassword("akylbek01");
        userService.registration(userRequest);
    }

    @Test
    void findAll() {
        try {
            newUser();
            List<User> userList = userService.findAll();
            if (userList.size() < 1) {
                Assertions.fail("There are no clients in the database");
            }

        } catch (Exception e) {
            Assertions.fail("An error occurred while getting clients: " + e.getMessage());
        }
    }

    @Test
    void registration() {
        try {
            newUser();
            if (Objects.equals(userRequest.getUserName(), userResponse.getUserName())
                    || Objects.equals(userRequest.getEmail(), userResponse.getEmail())) {
                Assertions.fail("There are several customers in the database with the same emails!\n");

            }
        } catch (Exception e) {
            Assertions.fail("An error occurred while registering clients: " + e.getMessage());
        }
    }

    @Test
    void findById() {
        try {
            newUser();
            User user = userService.findById(1L).get();
            assertThat(user).isNotNull();
            assertThat(user.getUserName()).isEqualTo("akylbeknurbekov01@gmail.com");
        } catch (Exception e) {
            Assertions.fail("An error occurred while getting a client by id: " + e.getMessage());
        }
    }

    @Test
    void deleteById() {
        try {
            newUser();
            userService.deleteById(1L);
        } catch (Exception e) {
            Assertions.fail("An error occurred while deleting clients by id: " + e.getMessage());
        }
    }

    @Test
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
}