package com.dev.inner.owntrip;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RetrofitService {
    public static final String BASE_URL = "http://112.158.99.134:3000/";

    @FormUrlEncoded
    @POST("users")
    Call<JsonObject> users(@Field("email") String email,
                           @Field("username")String username,
                           @Field("password") String password);


    @FormUrlEncoded
    @POST("login")
    Call<JsonObject> login(@Field("email") String email,
                           @Field("password") String password);

    @FormUrlEncoded
    @POST("search")
    Call<RetrofitResponse> search(@Header("Authorization")String token,
                                  @Field("tagName")String tagName);

}
