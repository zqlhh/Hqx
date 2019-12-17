package com.funyoo.hqxApp.dao;

import com.funyoo.hqxApp.model.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleDao {

    @Select("SELECT * FROM appview_${part} ORDER BY id DESC LIMIT #{readNum}")
    public List<Article> getArticles(@Param("part") String part, @Param("readNum") int articleOnceReadNum);

    @Select("SELECT * FROM appview_${part} WHERE id < #{num} ORDER BY id DESC LIMIT #{readNum}")
    public List<Article> getArticlesByNum(@Param("part") String part, @Param("num") int num, @Param("readNum") int articleOnceReadNum);

    @Select("SELECT * FROM appview_${part} WHERE recommend = 2 ORDER BY id DESC LIMIT 1")
    public Article getTopArticle(@Param("part") String part);


    @Insert("INSERT INTO appview_${part} (title, type, html_url, recommend, pic_url, date) " +
            "VALUES (#{a.title}, #{a.type}, #{a.htmlUrl}, #{a.recommend}, #{a.picUrl}, #{a.date})")
    public boolean saveArticle(@Param("part") String part, @Param("a") Article article);

    @Select("SELECT * FROM appview_${part} WHERE id = #{id}")
    Article getArticleById(@Param("part") String part, @Param("id") Integer artId);
}