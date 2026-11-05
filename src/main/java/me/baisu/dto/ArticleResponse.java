package me.baisu.dto;

import lombok.Getter;
import me.baisu.domain.Article;

@Getter
public class ArticleResponse {
    private String title;
    private String content;

//    public String getTitle() {
//        return title;
//    }
    public ArticleResponse(Article entity) {
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }
}
