package kg.peaksoft.bilingualb4.api.controller;

import kg.peaksoft.bilingualb4.api.payload.UserRequest;
import kg.peaksoft.bilingualb4.model.entity.User;
import kg.peaksoft.bilingualb4.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
@RunWith(SpringRunner.class)
@WebMvcTest(UserApi.class)
class UserApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    void findAll() throws Exception {
        when(userRepository.findAll()).thenReturn(Arrays.asList(
                new User("akylbek","akylbek@gmal.com","akylbek01"),
                new User("talgar","akylbek@gmal.com","akylbek01")
        ));

        mockMvc.perform(get("/api"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
        ;
    }

    @Test
    void findById() {
    }
}