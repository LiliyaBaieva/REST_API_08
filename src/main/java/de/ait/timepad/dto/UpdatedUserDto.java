package de.ait.timepad.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Data for updating")
public class UpdatedUserDto {

    @Schema(description = "Users role: USER, MANAGER", example = "USER")
    private String newRole;

    @Schema(description = "Users state: NOT_CONFIRMED, CONFIRMED, BANNED, DELETED", example = "NOT_CONFIRMED")
    private String newState;


}
