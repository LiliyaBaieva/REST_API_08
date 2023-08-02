package de.ait.timepad.dto;

import de.ait.timepad.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "User of system")
public class UserDto {

    @Schema(description = "Users id", example = "1")
    private Long id;

    @Schema(description = "Users email", example = "example@mail.com")
    private String email;

    @Schema(description = "Users role: ADMIN, USER, MANAGER", example = "USER")
    private String role;

    @Schema(description = "Users state: NOT_CONFIRMED, CONFIRMED, BANNED, DELETED", example = "NOT_CONFIRMED")
    private String state;

    public static UserDto from(User user){
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .state(user.getState().name())
                .role(user.getRole().name())
                .build();
    }

    public static List<UserDto> from(List<User> users){
        return users.stream()
                .map(UserDto::from)//user -> from(user)
                .collect(Collectors.toList());
    }

}












