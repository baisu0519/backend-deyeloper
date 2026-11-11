package me.baisu.controller;

import lombok.RequiredArgsConstructor;
import me.baisu.domain.Article;
import me.baisu.dto.AddArticleRequest;
import me.baisu.dto.ArticleResponse;
import me.baisu.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogApoController {
    private final BlogService blogService;

    @PostMapping("/api/articles")
//    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
//        Article savedArticle = blogService.save(request);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
//
//    }
    public ResponseEntity<ArticleResponse> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ArticleResponse(savedArticle));

    }
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> getAllArticles() {

//        List<Article> articleList = blogService.getAll();
//        List<ArticleResponse> articleResponseList = new ArrayList<>();
//        for(Article article : articleList) {
//            ArticleResponse ar = new ArticleResponse(article);
//            articleResponseList.add(ar);
//        }
        List<ArticleResponse> articleResponseList = blogService.getAll()
                .stream()
                //.map(a->new ArticleResponse(a))
                .map(ArticleResponse::new)
                .toList();
        return ResponseEntity.ok().body(articleResponseList);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> getArticle(
            @PathVariable Long id) {


        return ResponseEntity.ok()
                .body(new ArticleResponse(blogService.findById(id)));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        blogService.delete(id);
        return ResponseEntity.ok().build();
    }





//    public BlogApoController(blogService service){
//        this.blogService = service;
//      }
}
