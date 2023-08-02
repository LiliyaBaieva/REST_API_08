package de.ait.timepad.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Data to add new user")
@Data
public class NewUserDto {
    @Schema(description = "Users email", example = "example@mail.com")
    private String email;
    @Schema(description = "Users password", example = "password007")
    private String password;

}
