package me.baisu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.baisu.domain.Article;
import me.baisu.dto.AddArticleRequest;
import me.baisu.repository.BlogReepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BlogReepository blogReepository;

    @DisplayName("成功添加博客文章")
    @Test
    public void addArticle()throws Exception{
        //given:数据，请求URL
        String title = "11/4周三老登的课";
        String content = "进行了将数据储存到DB的练习";
        AddArticleRequest req = new AddArticleRequest(title,content);
        String reqBody = objectMapper.writeValueAsString(req);
        String url = "/api/articles";

        //when：发布文章请求
        ResultActions result = mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON_VALUE).content(reqBody));

        //then:数据库中是否已插入一条记录，且该记录的内容与上述内容一样
        result.andExpect(status().isCreated());
        List<Article> articles = blogReepository.findAll();
        assertThat(articles.size()).isEqualTo(1);
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);


    }

}