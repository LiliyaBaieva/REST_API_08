package de.ait.timepad.controllers;

import de.ait.timepad.models.Event;
import de.ait.timepad.repositories.EventRepository;
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
class EventControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EventRepository eventRepository;

    @BeforeEach
    public void setup(){
        eventRepository.clear();
    }

    @Test
    void addEvent() throws Exception {
        mockMvc.perform(post("/api/events")
                .header("Content-Type", "application/json")
                .content("{\n" +
                        "  \"title\": \"Team Building\",\n" +
                        "  \"date\": \"06.08.2023\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Team Building")))
                .andExpect(jsonPath("$.date", is("06.08.2023")));
    }

    @Test
    void getAllEventsTest() throws Exception {
        eventRepository.save(Event.builder().id(3L).title("First").date("01.01.2024").build());
        eventRepository.save(Event.builder().id(4L).title("Second").date("02.01.2024").build());

        mockMvc.perform(get("/api/events")).andDo(print())
                .andExpect(jsonPath("$.count", is(2)));
    }
}








