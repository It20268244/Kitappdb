package com.example.addarticle;

public class article {
    String articleName , publisherName , publishDate, articleType , article;

    article(){

    }

    public article(String articleName, String publisherName, String articleType) {
        this.articleName = articleName;
        this.publisherName = publisherName;
        this.articleType = articleType;
    }

    public article(String articleName, String publisherName, String publishDate, String articleType, String article) {
        this.articleName = articleName;
        this.publisherName = publisherName;
        this.publishDate = publishDate;
        this.articleType = articleType;
        this.article = article;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }
}
