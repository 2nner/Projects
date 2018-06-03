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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterUser extends AppCompatActivity {

    EditText email, password, chkpassword;
    String str_email, str_password, str_chkpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        initView();
    }

    public void initView() {
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        chkpassword = findViewById(R.id.et_chkPassword);

        findViewById(R.id.iv_btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_email = email.getText().toString();
                str_password = password.getText().toString();
                str_chkpassword = chkpassword.getText().toString();

                if(str_password.equals(str_chkpassword)) {
                    doRegister(str_email, str_password);
                }
                else {
                    Toast.makeText(getApplicationContext(), "비밀번호를 다시 확인해 주세요!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void doRegister(String str_email, String str_password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RequestInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        final Call<Void> response = requestInterface.signup(new User(str_email, str_password));
        response.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch(response.code()) {
                    case 201: {
                        finish();
                        Intent intent = new Intent(RegisterUser.this, RegisterUserInfo.class);
                        intent.putExtra("email", email.getText().toString());
                        startActivity(intent);
                        break;
                    }
                    case 409: {
                        Toast.makeText(getApplicationContext(), "중복된 이메일입니다!", Toast.LENGTH_LONG);
                        break;
                    }
                    default: {
                        Log.d("signup", "Code : " + response.code());
                        Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
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
