package me.baisu.repository;

import me.baisu.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

//@Repository
public interface BlogReepository extends JpaRepository<Article, Long> {
}
