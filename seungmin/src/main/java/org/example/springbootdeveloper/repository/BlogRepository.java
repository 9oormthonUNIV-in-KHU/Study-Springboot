package org.example.springbootdeveloper.repository;

import org.example.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

//public class BlogRepository {
//}

public interface BlogRepository extends JpaRepository<Article, Long>{

}