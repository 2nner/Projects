package com.highton.inner.sporrow;

import com.highton.inner.sporrow.login.User;
import com.highton.inner.sporrow.login.UserInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RequestInterface {

    public static final String BASE_URL = "http://52.79.202.248:1026/";

    @POST("signup")
    Call<Void> signup(@Body User user);

    @POST("/info/initialize/{email}")
    Call<Void> uploadUserInfo(@Path("email") String email,
                              @Body UserInfo userinfo);

    @POST("auth")
    Call<Void> auth(@Body User user);
}
