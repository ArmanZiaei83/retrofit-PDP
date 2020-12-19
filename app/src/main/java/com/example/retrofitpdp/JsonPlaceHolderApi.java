package com.example.retrofitpdp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PATCH;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

    @PUT("posts/{id}")
    Call<Post> putPost(@Path("id") int id , @Body Post post);
}
