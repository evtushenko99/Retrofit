package com.example.admin.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.admin.retrofit.MyPost.getSha256;


public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private TextView textView;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view);
        textViewResult = findViewById(R.id.text_view_result);
        textView.setText(getSha256("12345678").toUpperCase());
        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("https://jsonplaceholder.typicode.com/")
                .baseUrl("https://api-smartpot.ddns.net:4444/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        //getPosts();

        //getComments();
        //createPosts();
        createPostsAuth();
    }

    private void getPosts() {
        Map<String, String> parametrs = new HashMap();
        parametrs.put("userId", "1");
        parametrs.put("_sort", "id");
        parametrs.put("_order", "desc");
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(parametrs);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code:" + response.code());
                    return;
                }

                List<Post> posts = response.body();

                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserID() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText((t.getMessage()));
            }
        });

    }

    private void getComments() {
        Call<List<Comment>> call = jsonPlaceHolderApi.getComments(3);

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Comment> comments = response.body();

                for (Comment comment : comments) {
                    String content = "";
                    content += "ID: " + comment.getId() + "\n";
                    content += "Post ID: " + comment.getPostId() + "\n";
                    content += "Name: " + comment.getName() + "\n";
                    content += "Email: " + comment.getEmail() + "\n";
                    content += "Text: " + comment.getText() + "\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void createPosts() {
        // Post post = new Post(23,"New title","New text");
        Map<String, String> fields = new HashMap<>();
        fields.put("userId", "25");
        fields.put("title", "New Title");


        Call<Post> call = jsonPlaceHolderApi.createPost(fields);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                Post postResponse = response.body();

                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "User ID: " + postResponse.getUserID() + "\n";
                content += "Title: " + postResponse.getTitle() + "\n";
                content += "Text: " + postResponse.getText() + "\n\n";
                textViewResult.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void createPostsAuth() {
        // Post post = new Post(23,"New title","New text");
        //Call<MyPost> call = jsonPlaceHolderApi.createPostAuth("test@mail.com", getSha256("qwertyuiop").toUpperCase());
        Auth auth = new Auth("test2@mail.com", "12345678");
        Call<MyPost> call = jsonPlaceHolderApi.getAuth("test2@mail.com",getSha256("12345678").toUpperCase());
        call.enqueue(new Callback<MyPost>() {
            @Override
            public void onResponse(Call<MyPost> call, Response<MyPost> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                MyPost postResponse = response.body();

                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "Status: " + postResponse.getStatus() + "\n";
                content += "Result: " + postResponse.getResult() + "\n\n";
                textViewResult.setText(content);
            }

            @Override
            public void onFailure(Call<MyPost> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

    }
}
