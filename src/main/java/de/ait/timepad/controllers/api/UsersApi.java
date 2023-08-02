package de.ait.timepad.controllers.api;

import de.ait.timepad.dto.NewUserDto;
import de.ait.timepad.dto.UpdatedUserDto;
import de.ait.timepad.dto.UserDto;
import de.ait.timepad.dto.UsersDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tags(value = {
        @Tag(name = "Users")
})
@RequestMapping("/api/users")
public interface UsersApi {

    //    @ResponseBody - назначили в классе @RestController, чтоб не писат для каждого метода
// показывает, что нужно вернуть данные в теле ответе в формате JSON
    @Operation(summary = "Create users", description = "only available to administrator")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UserDto addUsers(@Parameter(required = true, description = "User") @RequestBody NewUserDto newUser); //нужно вытащить данные из тела в формате JSON

    @Operation(summary = "Get all users", description = "Available for everyone")
    @GetMapping
    UsersDto getAllUsers();

    @Operation(summary = "Delete user", description = "only available to administrator")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "The User is not found",
                    content = {@Content()}),
            @ApiResponse(responseCode = "200", description = "The user is deleted", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
            })
    })
    @DeleteMapping("/{user-id}") // переменная пути
    UserDto deleteUser(@Parameter(required = true, description = "Id of user", example = "1")
            @PathVariable("user-id") Long userId);


    @Operation(summary = "Update user", description = "only available to administrator")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "The User is not found",
                    content = {@Content()}),
            @ApiResponse(responseCode = "403", description = "Not allowed to make an administrator",
                    content = {@Content}),
            @ApiResponse(responseCode = "200", description = "The user is updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
            })
    })
    @PutMapping("/{user-id}") // переменная пути
    UserDto updateUser(@Parameter(required = true, description = "Id of user", example = "1")
            @PathVariable("user-id") Long userId,
            @RequestBody UpdatedUserDto updatedUser);

    @Operation(summary = "Get user", description = "available for everyone")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "The User is not found",
                    content = {@Content()}),
            @ApiResponse(responseCode = "200", description = "Get user", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
            })
    })
    @GetMapping("/{user-id}") // переменная пути
    UserDto getUser(@Parameter(required = true, description = "Id of user", example = "1")
            @PathVariable("user-id") Long userId);


}
