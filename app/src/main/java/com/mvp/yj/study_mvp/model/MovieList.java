package com.mvp.yj.study_mvp.model;

import com.google.gson.annotations.SerializedName;

public class MovieList {
    @SerializedName("title")
    String title;
    @SerializedName("link")
    String  link;
    @SerializedName("image")
    String image;
    @SerializedName("subtitle")
    String subtitle;
    @SerializedName("pubDate")
    String pubDate;
    @SerializedName("director")
    String director;
    @SerializedName("actor")
    String actor;
    @SerializedName("userRating")
    float userRating;


    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getImage() {
        return image;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getDirector() {
        return director;
    }

    public String getActor() {
        return actor;
    }

    public float getUserRating() {
        return userRating;
    }

    public MovieList() {
    }

    public MovieList(String title, String link, String image, String subtitle, String pubDate, String director, String actor, float userRating) {
        this.title = title;
        this.link = link;
        this.image = image;
        this.subtitle = subtitle;
        this.pubDate = pubDate;
        this.director = director;
        this.actor = actor;
        this.userRating = userRating;
    }
}
