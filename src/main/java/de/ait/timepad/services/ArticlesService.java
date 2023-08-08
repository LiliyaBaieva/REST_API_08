package de.ait.timepad.services;

import de.ait.timepad.dto.ArticleDto;
import de.ait.timepad.dto.ArticlesDto;
import de.ait.timepad.dto.NewArticleDto;

public interface ArticlesService {
    ArticleDto addArticle(NewArticleDto newArticle);

    ArticlesDto getArticle(Integer year, Integer month, Integer day);
}
