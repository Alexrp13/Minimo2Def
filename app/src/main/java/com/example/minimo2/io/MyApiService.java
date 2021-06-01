package com.example.minimo2.io;
import com.example.minimo2.responses.Badge;
import com.example.minimo2.responses.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyApiService {

    @GET("user/{username}")
    Call<UserResponse> getUserInfo(@Path("username") String username );

    @GET("badges/")
    Call<List<Badge>> getBadges();
}
