package me.baisu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.baisu.domain.Article;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddArticleRequest {
    private String title;
    private String content;

//    public String getTitle() {return title;}
//    public String getContent() {return content;}

//    public AddArticleRequest(){super();}
//    public AddArticleRequest(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }

    public Article toEntity() {
        return new Article(title, content);

//        return Article.builder().title(title).content(content).build();
    }

}
