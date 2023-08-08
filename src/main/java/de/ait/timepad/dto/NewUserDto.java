package de.ait.timepad.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Schema(description = "Data to add new user")
@Data
public class NewUserDto {
    @Schema(description = "Users email", example = "example@mail.com")
    @Email
    @NotNull
    @NotBlank
    private String email;

    @Schema(description = "Users password", example = "password007")
    @NotBlank
    @Size(min = 8, max = 20)
    private String password;

}
