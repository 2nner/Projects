package com.highton.inner.sporrow.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.highton.inner.sporrow.R;

public class RegisterUser extends AppCompatActivity {

    EditText password, chkpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        initView();
    }

    public void initView() {
        password = findViewById(R.id.et_password);
        chkpassword = findViewById(R.id.et_chkPassword);

        findViewById(R.id.iv_btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(password.getText().toString().equals(chkpassword.getText().toString())) {
                    startActivity(new Intent(RegisterUser.this, RegisterUserInfo.class));
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "비밀번호를 다시 확인해 주세요!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
