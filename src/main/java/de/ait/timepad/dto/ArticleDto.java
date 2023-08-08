package de.ait.timepad.dto;

import de.ait.timepad.models.Article;
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
@Schema(description = "Article of user")
public class ArticleDto {

    @Schema(description = "Article id", example = "1")
    private Long id;

    @Schema(description = "Text of article", example = "Article about user...")
    private String text;

    @Schema(description = "User id")
    private UserInArticleDto about;

    @Schema(description = "Date for publish", example = "2023-03-27")
    private String publishDate;

    public static ArticleDto from(Article article){
        ArticleDto result = ArticleDto.builder()
                .id(article.getId())
                .text(article.getText())
                .build();

        if(article.getAbout() != null){
            result.setAbout(UserInArticleDto.from(article.getAbout()));
        }

        if(article.getPublishDate() != null){
            result.setPublishDate(article.getPublishDate().toString());
        }

        return result;
    }

    public static List<ArticleDto> from(List<Article> articles){
        return articles.stream()
                .map(ArticleDto::from)
                .collect(Collectors.toList());
    }

}



















