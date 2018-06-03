package com.highton.inner.sporrow.login;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RequestInterface {

    public static final String baseUrl = "http://52.79.202.248:1026";

    @FormUrlEncoded
    @POST("/signup")
    Call<ServerResponse> SignUp(@Field("email") String email,
                                @Field("pw") String pw);

    @FormUrlEncoded
    @POST("/info/initialize/{email}")
    Call<ServerResponse> UploadUserInfo(@Path("email") String email,
                              @Field("nickname") String nickname,
                              @Field("categories") ArrayList<String> categories);
}
