package com.dev.inner.owntrip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.JsonObject;

import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText et_login_id, et_login_pw;
    Button bt_login_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_login_id = findViewById(R.id.et_login_id);
        et_login_pw = findViewById(R.id.et_login_pw);

        bt_login_send = findViewById(R.id.bt_login_send);

        bt_login_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(et_login_id.getText().toString(), et_login_pw.getText().toString());
            }
        });

    }

    public void login(String email, String password) {
        RetrofitService service = RetrofitCreator.createService(RetrofitService.class);

        retrofit2.Call<JsonObject> response = service.login(email, password);

        response.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(retrofit2.Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("login", "onResponse: " + response.code());

            }

            @Override
            public void onFailure(retrofit2.Call<JsonObject> call, Throwable t) {

            }
        });

    }
}
