package de.ait.timepad.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "List of users")
public class UsersDto {
    @Schema(description = "Users of system")
    private List<UserDto> users;

    @Schema(description = "Numbers of users", example = "1")
    private Integer count;
}
