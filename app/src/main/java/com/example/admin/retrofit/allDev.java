package com.example.admin.retrofit;

import com.google.gson.annotations.SerializedName;



public class allDev {
    @SerializedName("body")
    private String text;

    public String getText() {
        return text;
    }
}
