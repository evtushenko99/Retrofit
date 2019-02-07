package com.example.admin.retrofit;

import java.io.File;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface JsonPlaceHolderApi {
    @GET("posts")
    Call<List<Post>> getPosts(
            @Query("userId") Integer[] userId,

            @Query("_sort") String sort,
            @Query("_order") String order
    );

    @GET("posts")
    Call<List<Post>> getPosts(@QueryMap Map<String,String> parametrs);

    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id")int postId);

    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String text
    );

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@FieldMap Map<String,String> fields);

    @FormUrlEncoded
    @POST("auth")
    Call<MyPost> createPostAuth(
            @Field("Email") String email,
            @Field("Password") String password
    );

    @FormUrlEncoded
    @GET("alldev")
    Call<List<allDev>> getPosts(
            @Field("Authorization") String userToken
    );
    @POST("auth")
    Call<MyPost> getAuth(@Body Auth auth);

    @POST("auth")
    Call<MyPost> getAuth(@Header("Email") String email, @Header("Password") String password);
}
