package com.dev.inner.owntrip;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitService {
    public static final String BASE_URL = "";

    @FormUrlEncoded
    @POST("api/login")
    Call<JsonObject> login(@Field("id") String id,
                           @Field("pw") String pw);

}
