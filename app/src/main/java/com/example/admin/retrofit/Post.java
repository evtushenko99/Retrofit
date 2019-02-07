package com.example.admin.retrofit;

import com.google.gson.annotations.SerializedName;


public class Post {

    private int userID;

    private Integer id;
    private String title;
    @SerializedName("body")
    private String text;

    public Post(int userID, String title, String text) {
        this.userID = userID;
        this.title = title;
        this.text = text;
    }

    public int getUserID() {
        return userID;
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

}
