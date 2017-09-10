package com.example.pc.forwardcam_new;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestInterface {

    @POST("indexx.php/")
    Call<ServerResponse> operation(@Body ServerRequest request);

}
