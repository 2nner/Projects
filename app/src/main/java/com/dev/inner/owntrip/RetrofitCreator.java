package com.dev.inner.owntrip;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCreator {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(RetrofitService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build());

    private static Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }


}
