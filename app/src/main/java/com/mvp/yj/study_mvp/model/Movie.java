package com.mvp.yj.study_mvp.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Movie {
    @SerializedName("lastBuildDate")
    public String lastBuildDate;

    @SerializedName("total")
    public int total;

    @SerializedName("start")
    public int start;
    @SerializedName("display")
    public int display;
    @SerializedName("items")
    public ArrayList<MovieList> items;
}
