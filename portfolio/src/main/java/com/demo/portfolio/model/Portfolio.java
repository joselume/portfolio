package com.demo.portfolio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name="portfolio")
public class Portfolio {
    @Id
    @Column(name="idportfolio")
    private int id;

    @Column(name="image_url")
    @NotBlank(message = "Image field is required")
    private String image;

    @NotBlank(message = "Title field is required")
    private String title;

    @NotBlank(message = "Description field is required")
    private String description;


    @Column(name="twitter_user_name")
    @NotBlank(message = "Twitter User field is required")
    private String twitterUser;

    public Portfolio() {
    }

    public Portfolio(int id, String image, String title, String description, String twitterUser) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.description = description;
        this.twitterUser = twitterUser;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTwitterUser() {
        return twitterUser;
    }

    public void setTwitterUser(String twitterUser) {
        this.twitterUser = twitterUser;
    }
}
