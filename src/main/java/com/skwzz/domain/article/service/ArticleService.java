package com.skwzz.domain.article.service;

import com.skwzz.domain.article.entity.Article;
import com.skwzz.domain.article.entity.ArticleTag;
import com.skwzz.domain.article.entity.Tag;
import com.skwzz.domain.article.payload.request.CreateArticleRequestDTO;
import com.skwzz.domain.article.repository.ArticleRepository;
import com.skwzz.domain.article.repository.ArticleTagRepository;
import com.skwzz.domain.article.repository.TagRepository;
import com.skwzz.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {

    private final TagService tagService;
    private final ArticleTagService articleTagService;
    private final ArticleTagRepository articleTagRepository;
    private final ArticleRepository articleRepository;


    @Transactional
    public Article createArticle(CreateArticleRequestDTO request, User user) {
        CreateArticleRequestDTO.Article requestArticle = request.getArticle();

        Article article = Article.builder()
                .slug(requestArticle.getTitle().replace(" ", "-"))
                .title(requestArticle.getTitle())
                .description(requestArticle.getDescription())
                .body(requestArticle.getBody())
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .user(user)
                .build();

        return articleRepository.save(article);
    }

    @Transactional
    public Article saveArticleWithTagsProcedure(CreateArticleRequestDTO request, User user) {
        CreateArticleRequestDTO.Article requestArticle = request.getArticle();

        Article article = Article.builder()
                .slug(requestArticle.getTitle().replace(" ", "-"))
                .title(requestArticle.getTitle())
                .description(requestArticle.getDescription())
                .body(requestArticle.getBody())
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .user(user)
                .build();

        Article newArticle = articleRepository.save(article);

        List<ArticleTag> articleTagList = new ArrayList<>();
        List<String> tagList = requestArticle.getTagList();

        for(String tagName : tagList){
            Tag tag = tagService.createTag(tagName);
            articleTagList.add(articleTagService.createArticleTag(newArticle, tag));
        }
        articleTagRepository.saveAll(articleTagList);
    }
}
