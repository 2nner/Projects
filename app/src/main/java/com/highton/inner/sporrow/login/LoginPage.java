package com.highton.inner.sporrow.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.highton.inner.sporrow.R;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        initView();
    }

    public void initView() {
        findViewById(R.id.tv_goRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(LoginPage.this, RegisterUser.class));
            }
        });
    }

    public void doLogin() {

    }
}
