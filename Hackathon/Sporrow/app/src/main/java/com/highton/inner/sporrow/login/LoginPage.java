package com.highton.inner.sporrow.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.highton.inner.sporrow.R;
import com.highton.inner.sporrow.RequestInterface;
import com.highton.inner.sporrow.home.HomePage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginPage extends AppCompatActivity {

    String str_email, str_pw;
    EditText email, pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        initView();
    }

    public void initView() {
        email = findViewById(R.id.et_loginID);
        pw = findViewById(R.id.et_loginPassword);

        findViewById(R.id.iv_btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_email = email.getText().toString();
                str_pw = pw.getText().toString();
                doLogin(str_email, str_pw);
            }
        });

        findViewById(R.id.tv_goRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginPage.this, RegisterUser.class));
            }
        });
    }

    public void doLogin(String email, String pw) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RequestInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        final Call<Void> response = requestInterface.auth(new User(email, pw));
        response.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("login", "Code : " + response.code());
                switch(response.code()) {
                    case 200: {
                        finish();
                        startActivity(new Intent(LoginPage.this, HomePage.class));
                        break;
                    }
                    default: {
                        Log.d("login", "Code : " + response.code());
                        Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "서버에 연결할 수 없습니다!", Toast.LENGTH_LONG);
            }
        });
    }
}
