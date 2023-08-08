package de.ait.timepad.controllers.api;

import de.ait.timepad.dto.ArticleDto;
import de.ait.timepad.dto.ArticlesDto;
import de.ait.timepad.dto.NewArticleDto;
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
     @Tag(name = "Articles")
})
@RequestMapping("/api/articles")
public interface ArticlesApi {

    @Operation(summary = "Create article about user", description = "Available for everyone")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "422", description = "User with this id doesn`t exist",
                    content = {
                        @Content()
                    }),
            @ApiResponse(responseCode = "201", description = "Article is created",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ArticleDto.class))
                })
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ArticleDto addArticle(@RequestBody NewArticleDto newArticleDto);

    @Operation(summary = "Get all articles", description = "Available for everyone")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of articles",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ArticlesDto.class))
                })
    })
    @GetMapping
    ArticlesDto getArticles(@Parameter(description = "Year", example = "2022") @RequestParam(value = "year", required = false) Integer year,
                            @Parameter(description = "month", example = "02") @RequestParam(value = "month", required = false)Integer month,
                            @Parameter(description = "day", example = "02") @RequestParam(value = "day", required = false)Integer day);

}
