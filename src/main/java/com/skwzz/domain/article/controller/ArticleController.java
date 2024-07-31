package com.skwzz.domain.article.controller;

import com.skwzz.domain.article.payload.request.CreateArticleRequestDTO;
import com.skwzz.domain.article.service.ArticleService;
import com.skwzz.domain.user.controller.auth.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<Boolean> createArticle(@RequestBody CreateArticleRequestDTO request,
                                                 @AuthenticationPrincipal AuthenticatedUser authenticatedUser){
        log.info(request.toString());
        log.info(authenticatedUser.getUser().toString());
        articleService.createArticle(request, authenticatedUser.getUser());
        return ResponseEntity.ok().body(true);
    }
}
