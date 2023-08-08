package de.ait.timepad.dto;

import de.ait.timepad.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data@Builder
@Schema(description = "Information about user in article")
public class UserInArticleDto {

    @Schema(description = "Users id", example = "1")
    private Long id;

    @Schema(description = "Users email", example = "example@mail.com")
    private String email;

    public static UserInArticleDto from(User user){
        return UserInArticleDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }

}
