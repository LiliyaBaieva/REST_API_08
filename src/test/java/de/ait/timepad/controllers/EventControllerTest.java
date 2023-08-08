package de.ait.timepad.controllers;

import de.ait.timepad.models.Event;
import de.ait.timepad.repositories.EventRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.DeleteMapping;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("EventController is works")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
class EventControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EventRepository eventRepository;

    @BeforeEach
    public void setup(){
        eventRepository.clear();
    }

    @Nested
    @DisplayName("POST /api/events method is works")
    class AddEventTests{
        @Test
        void add_event_test() throws Exception {
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
    }

    @Nested
    @DisplayName("GET /api/events/eventId method is works")
    class GetAllEventsTest{
        @Test
        void get_all_events_test() throws Exception {
            eventRepository.save(Event.builder().title("First").date("01.01.2024").build());
            eventRepository.save(Event.builder() .title("Second").date("02.01.2024").build());

            mockMvc.perform(get("/api/events")).andDo(print())
                    .andExpect(jsonPath("$.count", is(2)));
        }
    }

    @Nested
    @DisplayName("DELETE /api/events/eventId method is works")
    class DeleteEventsTests{
        @Test
        public void deleteExistEvent() throws Exception {
            eventRepository.save(Event.builder()
                    .title("Hallo").date("01.01.2022").build());
            mockMvc.perform(delete("/api/events/1"))
                    .andExpect(status().isOk());
        }

        @Test
        public void delete_not_exist_event() throws Exception {
            mockMvc.perform(delete("/api/events/1"))
                    .andExpect(status().isNotFound());

        }
    }

    @Nested
    @DisplayName("PUT /api/events/eventId method is works")
    class UpdateEventsTests{

        @Test
        public void update_exist_event_test() throws Exception {
            eventRepository.save(Event.builder()
                    .date("01.01.2023")
                    .title("Tests event").build());

            mockMvc.perform(put("/api/events/1")
                            .header("Content-Type", "application/json")
                            .content("{\n" +
                                    "  \"newDate\" : \"05.01.2023\"\n" +
                                    "}"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.title", is("Tests event")))
                    .andExpect(jsonPath("$.date", is("05.01.2023")));
        }

        @Test
        public void update_not_exist_event_test() throws Exception {
            mockMvc.perform(put("/api/events/1")
                            .header("Content-Type", "application/json")
                            .content("{\n" +
                                    "  \"newDate\" : \"05.01.2023\"\n" +
                                    "}"))
                    .andExpect(status().isNotFound());
        }

    }

    @Nested
    @DisplayName("GET /api/events/eventId is works")
    class GetEventTests{

        @Test
        public void get_exist_event_test() throws Exception {
            eventRepository.save(Event.builder()
                    .date("01.01.2023")
                    .title("Tests event").build());

            mockMvc.perform(get("/api/events/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.title", is("Tests event")))
                    .andExpect(jsonPath("$.date", is("01.01.2023")));
        }

        @Test
        public void get_not_exist_event_test() throws Exception {
            mockMvc.perform(get("/api/events/1"))
                    .andExpect(status().isNotFound());
        }

    }



}








