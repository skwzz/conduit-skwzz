package com.skwzz.domain.article.payload.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CreateArticleRequestDTO {

    private Article article;

    @Getter
    @Setter
    @ToString
    public static class Article {
        private String title;
        private String description;
        private String body;
        private List<String> tagList;
    }
}
