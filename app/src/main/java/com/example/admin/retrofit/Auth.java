package com.example.admin.retrofit;

import com.google.gson.annotations.SerializedName;

import static com.example.admin.retrofit.MyPost.getSha256;


public class Auth {
    @SerializedName("Email")
    private String email;
    @SerializedName("Password")
    private String password;

    public Auth(String email, String password) {
        this.email = email;
        this.password = getSha256(password).toUpperCase();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
