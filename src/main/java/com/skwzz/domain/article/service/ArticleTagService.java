package com.skwzz.domain.article.service;

import com.skwzz.domain.article.entity.Article;
import com.skwzz.domain.article.entity.ArticleTag;
import com.skwzz.domain.article.entity.Tag;
import com.skwzz.domain.article.repository.ArticleRepository;
import com.skwzz.domain.article.repository.ArticleTagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleTagService {

    private final ArticleTagRepository articleTagRepository;

    @Transactional
    public ArticleTag createArticleTag(Article article, Tag tag){
        ArticleTag articleTag = ArticleTag.builder()
                .article(article)
                .tag(tag)
                .build();
        return articleTagRepository.save(articleTag);
    }
}
