package de.ait.timepad.controllers;

import de.ait.timepad.models.User;
import de.ait.timepad.repositories.ArticleRepository;
import de.ait.timepad.repositories.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("ArticlesControllersTests is works")
public class ArticlesControllersTests {

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
    class AddArticleTEst{

        /*
{
  "id": 2,
  "text": "Have a good news",
  "about": {
    "id": 1,
    "email": "liliia@gmail.com"
  }
}
         */
        @Test
        public void add_article_for_exist_user() throws Exception {
            userRepository.save(User.builder().email("mail@mail.com").role(User.Role.USER).state(User.State.NOT_CONFIRMED)
                    .articles(new ArrayList<>()).build());
            mockMvc.perform(post("/api/articles")
                            .header("Content-Type", "application/json")
                            .content("{\n" +
                                    "  \"text\": \"Something nice about Liliia\",\n" +
                                    "  \"aboutUserId\": 1,\n" +
                                    "  \"publishDate\": \"2023-02-22\"\n" +
                                    "\n" +
                                    "}"))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.text", is("Something nice about Liliia")))
                    .andExpect(jsonPath("$.about.id", is(1)))
                    .andExpect(jsonPath("$.about.email", is("mail@mail.com")))
                    .andExpect(jsonPath("$.publishDate", is("2023-02-22")));

        }

        @Test
        public void add_article_for_not_exist_user() throws Exception   {
            mockMvc.perform(post("/api/articles")
                    .header("Content-Type", "application/json")
                    .content("{\n" +
                            "  \"text\": \"Something nice about Liliia\",\n" +
                            "  \"aboutUserId\": 1\n" +
                            "\n" +
                            "}"))
                    .andExpect(status().isUnprocessableEntity());

        }

    }



}
