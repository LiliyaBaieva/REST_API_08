package de.ait.timepad.controllers;

import de.ait.timepad.models.User;
import de.ait.timepad.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup(){
        userRepository.clear();
    }

    @Test
    void addUsers() throws Exception {
        mockMvc.perform(post("/api/users")
                .header("Content-Type", "application/json")
                .content("{\n" +
                        "  \"email\": \"liliia@gmail.com\",\n" +
                        "  \"password\": \"lilu1234\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.email", is("liliia@gmail.com")))
                .andExpect(jsonPath("$.role", is("USER")))
                .andExpect(jsonPath("$.state", is("NOT_CONFIRMED")));
    }

    @Test
    void  getAllUsersTest() throws Exception{
        userRepository.save(User.builder().id(3L).state(User.State.NOT_CONFIRMED).role(User.Role.USER).build());
        userRepository.save(User.builder().id(4L).state(User.State.NOT_CONFIRMED).role(User.Role.USER).build());

        mockMvc.perform(get("/api/users")).andDo(print())
                .andExpect(jsonPath("$.count", is(2)));
    }

}

