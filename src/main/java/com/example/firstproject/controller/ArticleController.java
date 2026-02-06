package com.example.firstproject.controller;

import com.example.firstproject.entity.Article;
import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.*;

import java.util.List;

@Slf4j   // 로깅을 위한 어노테이션 추가
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){

        System.out.println(form.toString());

        //1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        log.info(form.toString());
        //System.out.println(article.toString());
        //2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        // 3. 리다이렉트 경로 설정 (저장된 id를 활용)
        log.info("saved id: " + saved.getId());
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable long id, Model model){
        log.info("id = " + id);
        // 1. id를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        // 3. 뷰 페이지 반환하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        List<Article> articleEntityList = articleRepository.findAll();
        //List<Article> articleEntityList1 = (List<Article>) articleRepository.findAll();  다운캐스팅하여 사용하기
        model.addAttribute("articleList", articleEntityList);
        return "articles/index";
    }
}
