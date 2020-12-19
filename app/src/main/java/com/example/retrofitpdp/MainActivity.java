package com.example.retrofitpdp;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.MessagePattern;
import android.os.Bundle;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    JsonPlaceHolderApi jsonPlaceHolderApi;
    public TextView textViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = (TextView) findViewById(R.id.textViewResult);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        putPost();
    }

    public void putPost(){

        final Post post = new Post("Title" , "Hello World" , 1);
        Call<Post> call = jsonPlaceHolderApi.putPost(3, post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("Code : " + response.code());
                    return;
                }

                Post post1 = response.body();

                String content = "";
                content += "title :" + post1.getTitle() + "\n";
                content += "body :" + post1.getBody() + "\n";
                content += "userId :" + post1.getUserId() + "\n";
                content += "Id :" + post1.getId() + "\n\n";

                textViewResult.append(content);

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText("Error Throwable : " + t.getMessage());
            }
        });
    }
}