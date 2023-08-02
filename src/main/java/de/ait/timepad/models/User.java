package de.ait.timepad.models;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "events")
public class User {
    public enum Role{
        ADMIN, USER, MANAGER
    }
    public enum State{
        NOT_CONFIRMED, CONFIRMED, BANNED, DELETE
    }

    private Long id;

    private String email;
    private String password;
    private Role role;
    private State state;

    private List<Event> events;

}
