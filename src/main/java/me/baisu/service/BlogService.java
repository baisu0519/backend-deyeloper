package me.baisu.service;

import lombok.RequiredArgsConstructor;
import me.baisu.domain.Article;
import me.baisu.dto.AddArticleRequest;


import me.baisu.repository.BlogReepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogReepository blogRepository;

    public Article save(AddArticleRequest dto){
        return blogRepository.save(dto.toEntity());
    }
}
