package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ArticleForm {
    private Long id;
    private String title;
    private String content;

    /*
    @AllArgsConstructor 로 대체됨
    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }
*/

    /*
    lombok -> ToString으로 대체
    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
*/


    public Article toEntity(){
        return new Article(id, title, content);
    }
}
