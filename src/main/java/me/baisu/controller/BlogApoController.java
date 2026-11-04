package me.baisu.controller;

import lombok.RequiredArgsConstructor;
import me.baisu.domain.Article;
import me.baisu.dto.AddArticleRequest;
import me.baisu.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BlogApoController {
    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);

    }





//    public BlogApoController(blogService service){
//        this.blogService = service;
//      }
}
