package de.ait.timepad.controllers;

import de.ait.timepad.models.Article;
import de.ait.timepad.models.User;
import de.ait.timepad.repositories.ArticleRepository;
import de.ait.timepad.repositories.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("UserController is works")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @BeforeEach
    public void setup(){
        userRepository.clear();
        articleRepository.clear();
    }

    @Nested
    @DisplayName("POST /api/users is works")
    class AddUsersTests{
        @Test
        void add_users() throws Exception {
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
    }


    @Nested
    @DisplayName("GET /api/users method is works")
    class GetAllUsersTests{
        @Test
        void  get_all_users_test() throws Exception{
            userRepository.save(User.builder().state(User.State.NOT_CONFIRMED).role(User.Role.USER).build());
            userRepository.save(User.builder().state(User.State.NOT_CONFIRMED).role(User.Role.USER).build());

            mockMvc.perform(get("/api/users")).andDo(print())
                    .andExpect(jsonPath("$.count", is(2)));
        }
    }



    @Nested
    @DisplayName("DELETE /api/users/{userId} method is works")
    class DeleteUserTests{
        @Test
        public void delete_exist_user() throws Exception {
            userRepository.save(User.builder()
                    .email("Vasia@mail.com").state(User.State.NOT_CONFIRMED).role(User.Role.USER).build());
            mockMvc.perform(delete("/api/users/1"))
                    .andExpect(status().isOk());
        }
        @Test
        public void delete_not_exist_user() throws Exception {
            mockMvc.perform(delete("/api/users/1"))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("PUT /api/users/{userId} method is works")
    class UpdateUserTests{

        @Test
        public void update_exist_user() throws Exception {
            userRepository.save(User.builder().state(User.State.NOT_CONFIRMED)
                    .role(User.Role.USER).build());

            mockMvc.perform(put("/api/users/1")
                    .header("Content-Type", "application/json")
                    .content("{\n" +
                            "  \"newRole\" : \"MANAGER\",\n" +
                            "  \"newState\" : \"CONFIRMED\"\n" +
                            "}"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.state", is("CONFIRMED")))
                    .andExpect(jsonPath("$.role", is("MANAGER")));
        }

        @Test
        public void update_not_exist_user() throws Exception {
            mockMvc.perform(put("/api/users/1")
                    .header("Content-Type", "application/json")
                    .content("{\n" +
                            "  \"newRole\" : \"MANAGER\",\n" +
                            "  \"newState\" : \"CONFIRMED\"\n" +
                            "}"))
                    .andExpect(status().isNotFound());
        }

        @Test
        public void update_user_as_admin() throws Exception {
            userRepository.save(User.builder().state(User.State.NOT_CONFIRMED)
                    .role(User.Role.USER).build());

            mockMvc.perform(put("/api/users/1")
                    .header("Content-Type", "application/json")
                    .content("{\n" +
                            "  \"newRole\" : \"ADMIN\",\n" +
                            "  \"newState\" : \"CONFIRMED\"\n" +
                            "}"))
                    .andExpect(status().isForbidden());
        }

    }

    @Nested
    @DisplayName("GET /api/users/{userId} is works")
    class GetUserTests{
        @Test
        public void get_exist_user() throws Exception {
            userRepository.save(User.builder().state(User.State.NOT_CONFIRMED)
                    .role(User.Role.USER).build());

            mockMvc.perform(get("/api/users/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.state", is("NOT_CONFIRMED")))
                    .andExpect(jsonPath("$.role", is("USER")));
        }

        @Test
        public void get_not_exist_user() throws Exception {
            mockMvc.perform(get("/api/users/1"))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("GET /api/users/{userId}/articles is works")
    class GetArticlesOfUserTest{

        @Test
        void get_articles_for_exist_user() throws Exception {
            initializeDataForTest();

            mockMvc.perform(get("/api/users/1/articles"))
                    .andExpect(jsonPath("$.count", is(2)))
                    .andExpect(jsonPath("$.articles[0].id", is(1)))
                    .andExpect(jsonPath("$.articles[1].id", is(2)))
                    .andExpect(status().isOk());

        }

        private void initializeDataForTest() {
            User user = User.builder()
                    .email("mail@mail.com")
                    .role(User.Role.USER)
                    .state(User.State.NOT_CONFIRMED)
                    .articles(new ArrayList<>())
                    .build();

            Article article1 = Article.builder()
                    .id(1L)
                    .text("text 1")
                    .about(user)
                    .build();

            Article article2 = Article.builder()
                    .id(2L)
                    .text("text 2")
                    .about(user)
                    .build();

            articleRepository.save(article1); //обязательно сохранять в репо прежде чем добавлять в список
            articleRepository.save(article2);

            user.getArticles().add(article1);
            user.getArticles().add(article2);
            userRepository.save(user);
        }

        @Test
        void get_articles_for_not_exist_user() throws Exception {
            mockMvc.perform(get("/api/users/1/articles"))
                    .andExpect(status().isNotFound());
        }


    }

}

