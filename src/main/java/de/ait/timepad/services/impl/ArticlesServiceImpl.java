package de.ait.timepad.services.impl;

import de.ait.timepad.dto.ArticleDto;
import de.ait.timepad.dto.ArticlesDto;
import de.ait.timepad.dto.NewArticleDto;
import de.ait.timepad.exceptions.IncorrectUserIdException;
import de.ait.timepad.models.Article;
import de.ait.timepad.models.User;
import de.ait.timepad.repositories.ArticleRepository;
import de.ait.timepad.repositories.UserRepository;
import de.ait.timepad.services.ArticlesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static de.ait.timepad.dto.ArticleDto.from;

@RequiredArgsConstructor
@Service
public class ArticlesServiceImpl implements ArticlesService {

    private final UserRepository userRepository;

    private final ArticleRepository articleRepository;

    @Override
    public ArticleDto addArticle(NewArticleDto newArticle) {
        //получаем пользователя из репозитория или выбрасываем исключения
        User user = userRepository.findByID(newArticle.getAboutUserId())
            .orElseThrow(() -> new IncorrectUserIdException(
                "User with " + newArticle.getAboutUserId() + " does not exist"));

        // создём модель article с нужными данными
        Article article = Article.builder()
                .text(newArticle.getText()) //берём его текст из dto
                .about(user) //вставляем модель пользователя, кот получили на пред шаге
                .publishDate(LocalDate.parse(newArticle.getPublishDate())) // нужно из строки распарсить дату
                .build(); //создаём объект

        //берём список всех статей о пользователе и добавляем туда статью
        user.getArticles().add(article);

        //сохраняем статью в репозитории статей
        articleRepository.save(article);

        //возвращаем результат - преобразуем модель Article в ArticleDto
        return from(article);
    }

    @Override
    public ArticlesDto getArticle(Integer year, Integer month, Integer day) {
        if(isCorrect(year, month, day)){
            List<Article> articles = articleRepository.findAllByDate(year, month, day);
            return ArticlesDto.builder()
                    .articles(from(articles))
                    .count(articles.size())
                    .build();
        } else throw new IllegalArgumentException("Not correct dates format");
    }

    private boolean isCorrect(Integer year, Integer month, Integer day){
        return year == null && month == null && day == null ||
                year != null && month == null && day == null ||
                year != null && month != null && day == null ||
                year != null && month != null && day != null;
    }
}
